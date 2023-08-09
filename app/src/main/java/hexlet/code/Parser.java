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

        if (fileToRead.length() == 0) {
            throw new Exception("The file is empty!");
        } else if (pathname.endsWith(".json")) {
            ObjectMapper mapperJson = new ObjectMapper();
            results = mapperJson.readValue(fileToRead, new TypeReference<>() { });
        } else if (pathname.endsWith(".yml") || pathname.endsWith(".yaml")) {
            ObjectMapper mapperYaml = new YAMLMapper();
            results = mapperYaml.readValue(fileToRead, new TypeReference<>() { });
        }

        return results;
    }
}
