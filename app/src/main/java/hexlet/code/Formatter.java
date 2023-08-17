package hexlet.code;

import hexlet.code.formatters.Format;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

import static hexlet.code.formatters.JsonFormatter.JSON_NAME;
import static hexlet.code.formatters.Plain.PLAIN_NAME;
import static hexlet.code.formatters.Stylish.STYLISH_NAME;

public class Formatter {
    public static Map<String, Format> formatters = Map.of(
            STYLISH_NAME, new Stylish(),
            PLAIN_NAME, new Plain(),
            JSON_NAME, new JsonFormatter()
            );

    public static String chooseFormat(String name, List<Item> items) throws Exception {
        Format choice = formatters.get(name);
        return choice.format(items);
    }
}
