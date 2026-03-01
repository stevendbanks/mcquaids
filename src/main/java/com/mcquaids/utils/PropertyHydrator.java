package com.mcquaids.utils;

import java.util.HashMap;
import java.util.Map;

import com.mcquaids.model.lookup.CodeValues;

public class PropertyHydrator {

    /**
     * Takes a JSON string from the DB and returns a hydrated Map<String,String>
     * where FK values are replaced with display text using CodeValues.
     */
    public static Map<String, String> hydrateFromJson(String propertiesJson) {

        Map<String, String> rawProps;

        if (propertiesJson != null && !propertiesJson.isEmpty()) {
            rawProps = JsonUtils.setPropertiesFromJson(propertiesJson);
        } else {
            rawProps = new HashMap<>();
        }

        return hydrate(rawProps);
    }

    /**
     * Takes a raw property map and returns a hydrated version.
     */
    public static Map<String, String> hydrate(Map<String, String> rawProps) {

        Map<String, String> hydrated = new HashMap<>();

        for (var entry : rawProps.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            // Use your existing CodeValues lookup
            String displayValue = CodeValues.getKeyValue(key, value);

            hydrated.put(key, displayValue);
        }

        return hydrated;
    }
    
    /**
     * Takes a raw property map and returns a hydrated version.
     */
    public static String hydrate( int equipmentType, String equipmentSubType) {

         String hydrated;

         // Use your existing CodeValues lookup
         CodeValues cv = new CodeValues();
        String displayValue = cv.getEquipmentSubTypeText(equipmentType, equipmentSubType);

        hydrated = displayValue;

        return hydrated;
    }
    
    
    
}