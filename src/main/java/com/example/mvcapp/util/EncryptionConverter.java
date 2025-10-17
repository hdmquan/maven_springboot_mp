// Alan Huynh | s1557984 | alan@protoflow.com.au

package com.example.mvcapp.util;

import java.util.Base64;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EncryptionConverter implements AttributeConverter<String, String> {
    
    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute == null) return null;
        return Base64.getEncoder().encodeToString(attribute.getBytes());
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return new String(Base64.getDecoder().decode(dbData));
    }
}
