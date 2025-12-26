package com.jayant.catalog_service.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayant.catalog_service.dto.SeatLayout;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SeatLayoutConverter implements AttributeConverter<SeatLayout, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(SeatLayout attribute) {
        if(attribute == null) return null;

        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting SeatLayout to JSON", e);
        }
    }

    @Override
    public SeatLayout convertToEntityAttribute(String dbData) {
        if(dbData == null || dbData.isEmpty()) return null;

        try {
            return objectMapper.readValue(dbData, SeatLayout.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to SeatLayout", e);
        }
    }
}
