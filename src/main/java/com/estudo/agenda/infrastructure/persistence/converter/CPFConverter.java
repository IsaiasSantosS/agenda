package com.estudo.agenda.infrastructure.persistence.converter;

import com.estudo.agenda.shared.VOs.CPF;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter (autoApply = false)
public class CPFConverter implements AttributeConverter<CPF, String>{
    
    @Override
    public String convertToDatabaseColumn(CPF attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.cpfSemFormatacao();
    }

    @Override
    public CPF convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return new CPF(dbData);
    }
}
