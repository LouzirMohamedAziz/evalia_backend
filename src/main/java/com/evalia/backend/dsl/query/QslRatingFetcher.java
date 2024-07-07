package com.evalia.backend.dsl.query;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.evalia.backend.exceptions.ValueConversionException;
import com.evalia.backend.metadata.Performance;
import com.evalia.backend.models.QRating;
import com.evalia.backend.models.Rating;
import com.evalia.backend.util.Constants;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.TemporalExpression;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class QslRatingFetcher {

	public static final String RATING_DATE = "date";
	public static final String INDICATOR = "indicator";
	public static final String PERFORMANCE = "performance";
	public static final String PROFESSIONAL = "professional";
	public static final String SECTOR = "sector";
	public static final String SUB_SECTOR = "subsector";
	public static final String GOVERNORATE = "governorate";
	public static final String DELEGATION = "delegation";

	private static final SimpleDateFormat FORMATTER_1;
	private static final SimpleDateFormat FORMATTER_2;
	private static final QRating rating = QRating.rating;
	private static final Map<String, SimpleExpression<?>> bindings;

	static {
		FORMATTER_1 = new SimpleDateFormat("dd-MM-yyyy");
		FORMATTER_2 = new SimpleDateFormat("dd/MM/yyyy");
		bindings = Map.of(RATING_DATE, rating.date, // Date
				INDICATOR, rating.indicator.name, // String
				PERFORMANCE, rating.indicator.performance, // Enum
				PROFESSIONAL, rating.evaluatee.identifier, // String
				SECTOR, rating.evaluatee.subSector.sector.name, // String
				SUB_SECTOR, rating.evaluatee.subSector.name, // String
				GOVERNORATE, rating.evaluatee.address.governorate.name, // String
				DELEGATION, rating.evaluatee.address.delegation.name // String
		);
	}

	
	@PersistenceContext
	private EntityManager em;
	

	private static Map<String, String> purgeCriterions(Map<String, String> criterions) {
		Iterator<Entry<String, String>> iterator = criterions.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			if (Objects.isNull(entry.getKey()) || entry.getKey().isBlank() || Objects.isNull(entry.getValue())
					|| entry.getValue().isBlank()) {
				iterator.remove();
			}
		}
		return criterions;
	}
	

	private static Date convertToDate(String value) throws ParseException {
		try {
			return FORMATTER_1.parse(value);
		} catch (ParseException e) {
			return FORMATTER_2.parse(value);
		}
	}
	

	private static Entry<String, Object> parseValue(String field, String value) {
		try {
			switch (field) {
				case RATING_DATE:
					return Map.entry(field, convertToDate(value));
				case PERFORMANCE:
					return Map.entry(field, Performance.valueOf(value.toUpperCase()));
				default:
					return Map.entry(field, value);
			}
		} catch (ParseException | IllegalArgumentException e) {
			String type = e instanceof ParseException ? Date.class.getTypeName() : Performance.class.getTypeName();
			throw ValueConversionException.build(value, type);
		}
	}
	

	private static Map<String, Object> parseCriterions(Map<String, String> criterions) {
		criterions = purgeCriterions(criterions);
		int i = 0;
		Entry<String, Object>[] parsedCriterions = new Entry[criterions.size()];
		for (Entry<String, String> entry : criterions.entrySet()) {
			parsedCriterions[i++] = parseValue(entry.getKey(), entry.getValue());
		}
		return Map.ofEntries(parsedCriterions);
	}
	

	private <T> BooleanExpression buildExpression(SimpleExpression<T> exp, T value) {
		Calendar calendar = Calendar.getInstance();
		if(value instanceof Date) {
			Date val = (Date) value;
			calendar.setTime(val);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			calendar.add(Calendar.SECOND, -1);
			Date from = val;
			Date to = calendar.getTime();
			return ((TemporalExpression)exp).between(from, to);
		}
		return exp.eq(value);
	}
	

	public List<Rating> fetch(Map<String, String> criterions) {
		Map<String, Object> parsedCriterions = parseCriterions(criterions);
		BooleanExpression exp = null;

		for (Entry<String, Object> entry : parsedCriterions.entrySet()) {
			SimpleExpression ex = bindings.get(entry.getKey());
			if (Objects.isNull(ex)) {
				String msg = MessageFormat.format(Constants.FIELD_NOT_RECOGNIZED, entry.getKey());
				throw new IllegalArgumentException(msg);
			}
			// each boolean experssion requires .and(Nullable property) to append another
			// condition
			
			exp = buildExpression(ex, entry.getValue()).and(exp);
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, em);
		return queryFactory
				.selectFrom(rating)
				.where(exp)
				.fetch();
	}
}
