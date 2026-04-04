package com.mcquaids.converters;


import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;
import java.time.LocalDate;
import java.util.Map;

public class LocalDateConverter extends DefaultTypeConverter {

    @Override
    public Object convertValue(Map<String, Object> context, Object value, Class toType) {

        if (toType == LocalDate.class) {
            String[] params = (String[]) value;
            return LocalDate.parse(params[0]);
        }

        if (toType == String.class && value instanceof LocalDate) {
            return value.toString();
        }

        return null;
    }
}