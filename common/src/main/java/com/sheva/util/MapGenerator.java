package com.sheva.util;

import java.util.HashMap;
import java.util.Map;

public class MapGenerator {

    public static Map<String, Object> convertObjectToMap(Object object) {

        Map<String, Object> model = new HashMap<>();
        model.put("result", object);

        return model;
    }
}
