package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Item;

import java.util.List;

public class JsonFormatter implements Format {
    public static final String JSON_NAME = "json";

    public String format(List<Item> items) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(items);
    }
}
