package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parseFile(String pathname) throws Exception {
        Map<String, Object> results = new HashMap<>() { };
        File fileToRead = new File(pathname);

        String[] str = pathname.split("\\.");

        switch (str[str.length - 1]) {
            case "json" -> {
                ObjectMapper mapperJson = new ObjectMapper();
                results = mapperJson.readValue(fileToRead, new TypeReference<>() { });
            }
            case "yml", "yaml" -> {
                ObjectMapper mapperYaml = new YAMLMapper();
                results = mapperYaml.readValue(fileToRead, new TypeReference<>() { });
            }
            default -> throw new Exception("Invalid format!");
        }

        return results;
    }
}
