package com.spring.springselenium.JsonTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.springselenium.JSONUtils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@SpringBootTest
public class JsonMapTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private JsonUtils jsonUtils;

    @Test
    public void CreateJson() throws IOException, IOException {
        String fileName = "JsonFile";
        Map<Object, Object> bookingDetails = new HashMap();
        bookingDetails.put("firstname", "Jim");
        bookingDetails.put("lastname", "Brown");
        bookingDetails.put("totalprice", 111);
        bookingDetails.put("depositpaid", true);
        bookingDetails.put("additionalneeds", "Breakfast");
        jsonUtils.CreateJsonFromMap(bookingDetails, fileName);
    }

    @Test
    public void ReadJson() throws IOException {
        Map<String, String> readData = jsonUtils.readJsonFromFile("JsonFile");
        Set<String> allKeys = readData.keySet();
        System.out.println("Stored values are ");
        Set<Map.Entry<String, String>> entrySet = readData.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    @Test
    public void ReadNestedJson() throws IOException {
        String jsonObject = "{\r\n" + "  \"firstName\": \"Animesh\",\r\n" + "  \"lastName\": \"Prashant\",\r\n"
                + "  \"address\": {\r\n" + "    \"city\": \"Katihar\",\r\n" + "    \"State\": \"Bihar\"\r\n" + "  }\r\n"
                + "}";
        Map<String, Object> readData = jsonUtils.readJsonFromFromString(jsonObject);
        Set<String> allKeys = readData.keySet();
        allKeys.stream().forEach(key -> {Object value = readData.get(key);
            if (value instanceof String)
                System.out.println(key);
            else if (value instanceof LinkedHashMap<?, ?>) {
                @SuppressWarnings("unchecked")
                Set<String> allKeysOfNestedJsonObject = ((LinkedHashMap<String, ?>) value).keySet();
                allKeysOfNestedJsonObject.stream().forEach(k -> System.out.println(k));
            }
        });
    }

}
