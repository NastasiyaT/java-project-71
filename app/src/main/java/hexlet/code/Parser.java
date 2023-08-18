package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parseFile(String pathname) throws Exception {
        File fileToRead = new File(pathname);

        String[] str = pathname.split("\\.");

        switch (str[str.length - 1]) {
            case "json" -> {
                ObjectMapper mapperJson = new ObjectMapper();
                return mapperJson.readValue(fileToRead, new TypeReference<>() { });
            }
            case "yml", "yaml" -> {
                ObjectMapper mapperYaml = new YAMLMapper();
                return mapperYaml.readValue(fileToRead, new TypeReference<>() { });
            }
            default -> throw new Exception("Invalid format!");
        }
    }
}
