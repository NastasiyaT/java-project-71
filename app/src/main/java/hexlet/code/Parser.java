package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parseData(String data, String type) throws Exception {

        Map<String, Object> result = new HashMap<>();

        if (type.equals("json")) {
            ObjectMapper mapperJson = new ObjectMapper();
            result = mapperJson.readValue(data, new TypeReference<>() {
            });
        } else if (type.equals("yml")) {
            ObjectMapper mapperYaml = new YAMLMapper();
            result = mapperYaml.readValue(data, new TypeReference<>() {
            });
        }

        return result;
    }
}
