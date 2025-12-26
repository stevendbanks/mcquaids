package com.mcquaids.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStringUtil {

	private static final String DATE_FORMAT = "yyyy-MM-dd";
    private  final DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
    
    public DateStringUtil() {
        formatter.setLenient(false);
    }
   
    public  String dateToString(Date o) {
        if (o == null) {
            return null;
        }
        return formatter.format(o);
    }

    public  Date stringToDate(String dateString) {
    	
    	System.out.println("StringToDate="+ dateString);
        if (dateString == null || dateString.equals("")) {
            return null;
        }
    	
        try {
        	Date dt = formatter.parse(dateString);
        	System.out.println("parsed Date="+dt.toString());
            return dt;
        } catch (Exception e) {
        	System.out.println("INVALID DATE");
        	e.printStackTrace();
            return null;
        }
    }
}
