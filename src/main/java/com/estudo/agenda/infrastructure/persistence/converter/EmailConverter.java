package com.estudo.agenda.infrastructure.persistence.converter;

import com.estudo.agenda.shared.VOs.Email;

import jakarta.persistence.AttributeConverter;

public class EmailConverter implements AttributeConverter<Email, String> {

    @Override
    public String convertToDatabaseColumn(Email attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getEmail();
    }

    @Override
    public Email convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return new Email(dbData);
    }
    
}
