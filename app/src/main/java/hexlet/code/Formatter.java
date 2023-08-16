package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;

public class Formatter {
    public static String chooseFormat(String format, List<Item> items) throws Exception {

        return switch (format) {
            case "stylish" -> Stylish.formatStylish(items);
            case "plain" -> Plain.formatPlain(items);
            case "json" -> JsonFormatter.formatJson(items);
            default -> throw new Exception("Invalid format!");
        };
    }
}
