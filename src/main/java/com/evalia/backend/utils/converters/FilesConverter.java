package com.evalia.backend.utils.converters;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import javax.persistence.PersistenceException;
import javax.sql.rowset.serial.SerialBlob;

@Converter
public class FilesConverter implements AttributeConverter<List<String>, List<Blob>> {

    private Encoder encoder = Base64.getEncoder();
    private Decoder decoder = Base64.getDecoder();

    @Override
    public List<Blob> convertToDatabaseColumn(List<String> contents) {
        List<Blob> blobs = new ArrayList<>();
        for (String content : contents) {
            try {
                blobs.add(new SerialBlob(decoder.decode(content)));
            } catch (SQLException e) {
                throw new PersistenceException(e);
            }
        }
        return blobs;
    }

    @Override
    public List<String> convertToEntityAttribute(List<Blob> blobs) {
        List<String> contents = new ArrayList<>();
        for (Blob blob : blobs) {
            try {
                String content = new String(encoder.encode(blob.getBinaryStream().readAllBytes()));
                contents.add(content);
            } catch (IOException | SQLException e) {
                throw new PersistenceException(e);
            }
        }
        return contents;
    }
}