package com.spring.springselenium.JsonTest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.springselenium.JSONUtils.JsonUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
public class ParseJsonTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private JsonUtils jsonUtils;

    @Test
    public void fetchJsonValue() throws SQLException, JSONException, IOException {
        JSONObject inputJSONOBject;
        String inputJson = "{\n" +
                "  \"myObjects\": [\n" +
                "    {\n" +
                "      \"code\": \"PQ\",\n" +
                "      \"another_objects\": [\n" +
                "        {\n" +
                "          \"attr1\": \"value1\",\n" +
                "          \"attr2\": \"value2\",\n" +
                "          \"attrN\": \"valueN\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"attr1\": \"value1\",\n" +
                "          \"attr2\": \"value2\",\n" +
                "          \"attrN\": \"valueN\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        String inputJson1 = "{\r\n"
                + "	\"address\": {\r\n"
                + "		\"zipCode\": 10045,\r\n"
                + "		\"city\": \"New York\",\r\n"
                + "		\"street\": \"155 Middleville Road\",\r\n"
                + "		\"state\": \"New York\"\r\n"
                + "	},\r\n"
                + "	\"projects\": [\r\n"
                + "		{\r\n"
                + "			\"title\": \"Business Website\",\r\n"
                + "			\"budget\": 4500\r\n"
                + "		},\r\n"
                + "		{\r\n"
                + "			\"title\": \"Sales Dashboard\",\r\n"
                + "			\"budget\": 8500\r\n"
                + "		}\r\n"
                + "	],\r\n"
                + "	\"paymentMethods\": [\r\n"
                + "		\"PayPal\",\r\n"
                + "		\"Stripe\"\r\n"
                + "	],\r\n"
                + "	\"name\": \"John Doe\",\r\n"
                + "	\"id\": 1,\r\n"
                + "	\"email\": \"john.doe@example.com\",\r\n"
                + "	\"age\": 32\r\n"
                + "}";
        String inputJson2 ="{\r\n"
                + "\"problems\": [{\r\n"
                + "    \"Diabetes\":[{\r\n"
                + "        \"medications\":[{\r\n"
                + "            \"medicationsClasses\":[{\r\n"
                + "                \"className\":[{\r\n"
                + "                    \"associatedDrug\":[{\r\n"
                + "                        \"name\":\"asprin\",\r\n"
                + "                        \"dose\":\"\",\r\n"
                + "                        \"strength\":\"500 mg\"\r\n"
                + "                    }],\r\n"
                + "                    \"associatedDrug#2\":[{\r\n"
                + "                        \"name\":\"somethingElse\",\r\n"
                + "                        \"dose\":\"\",\r\n"
                + "                        \"strength\":\"500 mg\"\r\n"
                + "                    }]\r\n"
                + "                }],\r\n"
                + "                \"className2\":[{\r\n"
                + "                    \"associatedDrug\":[{\r\n"
                + "                        \"name\":\"asprin\",\r\n"
                + "                        \"dose\":\"\",\r\n"
                + "                        \"strength\":\"500 mg\"\r\n"
                + "                    }],\r\n"
                + "                    \"associatedDrug#2\":[{\r\n"
                + "                        \"name\":\"somethingElse\",\r\n"
                + "                        \"dose\":\"\",\r\n"
                + "                        \"strength\":\"500 mg\"\r\n"
                + "                    }]\r\n"
                + "                }]\r\n"
                + "            }]\r\n"
                + "        }],\r\n"
                + "        \"labs\":[{\r\n"
                + "            \"missing_field\": \"missing_value\"\r\n"
                + "        }]\r\n"
                + "    }],\r\n"
                + "    \"Asthma\":[{}]\r\n"
                + "}]}";
        String inputJson3 ="{\r\n"
                + "\r\n"
                + " \"employee\":[\r\n"
                + "	{\r\n"
                + "		\"firstname\": \"Jim\",\r\n"
                + "		\"lastname\": \"Brown\",\r\n"
                + "		\"totalprice\": 111,\r\n"
                + "		\"depositpaid\": true,\r\n"
                + "		\"additionalneeds\": \"Breakfast\",\r\n"
                + "		\"bookingdates\": {\r\n"
                + "			\"checkin\": \"2021-07-01\",\r\n"
                + "			\"checkout\": \"2021-07-01\"\r\n"
                + "		}\r\n"
                + "	},\r\n"
                + "	{\r\n"
                + "		\"firstname\": \"Amod\",\r\n"
                + "		\"lastname\": \"Mahajan\",\r\n"
                + "		\"totalprice\": 222,\r\n"
                + "		\"depositpaid\": true,\r\n"
                + "		\"additionalneeds\": \"Breakfast\",\r\n"
                + "		\"bookingdates\": {\r\n"
                + "			\"checkin\": \"2022-07-01\",\r\n"
                + "			\"checkout\": \"2022-07-01\"\r\n"
                + "		}\r\n"
                + "	}\r\n"
                + "]\r\n"
                + "}";

        inputJSONOBject = new JSONObject(inputJson);
       List<Object> Jsonval1=jsonUtils.parseObject(inputJSONOBject, "attr1");
        System.out.println(Jsonval1.toString());

       inputJSONOBject = new JSONObject(inputJson1);
        List<Object> Jsonval2=jsonUtils.parseObject(inputJSONOBject, "title");
        System.out.println(Jsonval2.toString());

        inputJSONOBject = new JSONObject(inputJson2);
        List<Object> Jsonval3=jsonUtils.parseObject(inputJSONOBject, "strength");
        System.out.println(Jsonval3.toString());

        inputJSONOBject = new JSONObject(inputJson3);
        List<Object> Jsonval4=jsonUtils.parseObject(inputJSONOBject, "checkin");
        System.out.println(Jsonval4.toString());

        Reader reader = Files.newBufferedReader(Paths.get("JsonFiles/NestedJsonArray.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonTree = objectMapper.readTree(reader);
        inputJSONOBject = new JSONObject(jsonTree.toPrettyString());
        List<Object> Jsonval5=jsonUtils.parseObject(inputJSONOBject, "checkin");
        System.out.println(Jsonval5.toString());
    }

}
