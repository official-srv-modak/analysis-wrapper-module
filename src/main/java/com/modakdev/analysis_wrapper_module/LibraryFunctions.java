package com.modakdev.analysis_wrapper_module;

import org.json.simple.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class LibraryFunctions {

    public static JSONObject convertToJSONObject(LinkedHashMap<String, Object> map) {
        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            jsonObject.put(entry.getKey(), entry.getValue());
        }
        return jsonObject;
    }
}
