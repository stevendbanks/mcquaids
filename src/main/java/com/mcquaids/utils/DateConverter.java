package com.mcquaids.utils;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.conversion.TypeConversionException;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

public class DateConverter extends DefaultTypeConverter  {
	
	DateStringUtil ds = new DateStringUtil();

    @SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String[] values, Class toClass) {
        if (values != null && values.length > 0 && values[0] != null && values[0].length() > 0) {
            try {
                return ds.stringToDate(values[0]);
            }
            catch(Exception e) {
                throw new TypeConversionException(e);
            }
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
	public String convertToString(Map context, Object o) {
        if (o instanceof Date) {
            return ds.dateToString((Date)o);
        }
        return "";
    }
}