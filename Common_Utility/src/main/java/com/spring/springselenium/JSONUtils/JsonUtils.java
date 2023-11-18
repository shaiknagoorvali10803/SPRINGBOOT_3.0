package com.spring.springselenium.JSONUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Component
public class JsonUtils {
    public static List<Object> parseObject(JSONObject json, String key) {
        List<Object> jsonValues = new ArrayList<>();
        getKey(json, key, jsonValues);
        return jsonValues;
    }

    public void CreateJsonFromMap(Map<Object,Object> data,String fileName) throws IOException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonfile = new File("JsonFiles\\"+ fileName + ".json");
        String jsonArrayAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
        System.out.println(jsonArrayAsString);
        objectMapper.writeValue(jsonfile, data);
    }

    public Map<String, String> readJsonFromFile(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonfile = new File("JsonFiles\\"+ fileName + ".json");
        Map<String, String> dataRead = objectMapper.readValue(jsonfile, new TypeReference<Map<String, String>>() {
        });
        return dataRead;
    }

    public Map<String, Object> readJsonFromFromString(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> dataRead = objectMapper.readValue(jsonString,new TypeReference<Map<String, Object>>() {
        });
        return dataRead;
    }


    public static void getKey(JSONObject json, String key, List<Object> values) {
        boolean exists = json.has(key);
        Iterator<?> keys;
        String nextKeys;
        if (!exists) {
            keys = json.keys();
            while (keys.hasNext()) {
                nextKeys = (String) keys.next();
                try {
                    if (json.get(nextKeys) instanceof JSONObject) {
                        if (exists == false) {
                            getKey(json.getJSONObject(nextKeys), key, values);
                        }
                    } else if (json.get(nextKeys) instanceof JSONArray) {
                        JSONArray jsonarray = json.getJSONArray(nextKeys);
                        for (int i = 0; i < jsonarray.length(); i++) {
                            String jsonarrayString = jsonarray.get(i).toString();
                            JSONObject innerJSOn = new JSONObject(jsonarrayString);
                            if (exists == false) {
                                getKey(innerJSOn, key, values);
                            }
                        }
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        } else {
            values.add(json.get(key));
        }
    }
}
