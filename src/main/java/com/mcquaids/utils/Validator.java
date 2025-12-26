package com.mcquaids.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

 public class Validator {
	
	public  boolean isValid(String dateStr) {
		return  isValid(dateStr, "yyyy-MM-dd"); 
	}
 
	
	public boolean isValid(String dateStr, String dateFormat) {
		if (null == dateStr) {
			System.out.println("NULLL DATE");
			return true;
		}
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
        	System.out.println("Invalid Date="  + dateStr);
            return false;
        }
        System.out.println("VALID DATE =" + dateStr);
        return true;
    }	

}
