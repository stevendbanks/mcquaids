package com.mcquaids.utils;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class JsonUtils {
//    public static void setPropertiesFromJson(Object obj, String jsonString) {
//    	if (null == jsonString) {
//    		return;
//    	}
//        JSONObject jsonObject = new JSONObject(jsonString);
//        Field[] fields = obj.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            String fieldName = field.getName();
//            if (jsonObject.has(fieldName)) {
//                try {
//                    field.setAccessible(true);
//                    Class<?> fieldType = field.getType();
//                    if (fieldType == String.class) {
//                        field.set(obj, jsonObject.getString(fieldName));
//                    } else if (fieldType == int.class || fieldType == Integer.class) {
//                        field.set(obj, jsonObject.getInt(fieldName));
//                    } else if (fieldType == boolean.class || fieldType == Boolean.class) {
//                        field.set(obj, jsonObject.getBoolean(fieldName));
//                    }
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
    
	public static  Map<String, Object> setPropertiesFromJson(String json) {
	    JSONObject jsonObject = new JSONObject(json);
	    Map<String, Object> propertiesMap = jsonObject.toMap();

	    Map<String, Object> cleanedMap = new HashMap<>();
	    for (Map.Entry<String, Object> entry : propertiesMap.entrySet()) {
	        Object value = entry.getValue();
	        if (value instanceof java.util.List) {
	            java.util.List<?> list = (java.util.List<?>) value;
	            if (list.size() == 1) {
	                cleanedMap.put(entry.getKey(), list.get(0));
	            } else {
	                cleanedMap.put(entry.getKey(), list);
	            }
	        } else {
	            cleanedMap.put(entry.getKey(), value);
	        }
	    }

	    return  cleanedMap;
	}
    
    
    
}
