package com.mcquaids.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

public class JsonUtils  {

	private JsonUtils() {
	    // utility class
	}

	public static String toJson(Map<String, String> properties) {
	    if (properties == null) {
	        return "{}";
	    }
	    return new JSONObject(properties).toString();
	}

	public static Map<String, String> setPropertiesFromJson(String json) {
	    Map<String, String> map = new HashMap<>();
	    if (json == null || json.trim().isEmpty()) {
	        return map;
	    }

	    JSONObject obj = new JSONObject(json);
	    Iterator<String> keys = obj.keys();
	    while (keys.hasNext()) {
	        String key = keys.next();
	        Object value = obj.get(key);
	        map.put(key, value != null ? value.toString() : "");
	    }

	    return map;
	}
	
}