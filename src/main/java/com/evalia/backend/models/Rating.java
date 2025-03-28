package com.evalia.backend.models;

import java.io.File;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryInit;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Rating {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Double rate;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date date;

    @Column(length = 500)
    private String feedback;
    
    @Transient
    private String attachmentName;

    @JsonIgnore
    private String attachement;

    @OneToOne
    private Civil evaluater;

    @QueryInit({ "subSector.*", "address.*" })
    @ManyToOne(optional = false)
    private Professional evaluatee;

    @ManyToOne
    private Indicator indicator;

    public String getAttachmentName(){
    	
    	if(Objects.isNull(attachement)) {
    		return null;
    	}
    	
        if(Objects.isNull(attachmentName) || attachmentName.isBlank()){
            this.attachmentName = new File(attachement).getName();
        }
        return attachmentName;
    }

    public void setAttachement(String attachment){
        if(Objects.nonNull(attachment)){
            this.attachement = attachment;
            this.attachmentName = new File(attachment).getName();
        }
    }
}
