package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

public class Formatter {
    public static String chooseFormat(String format, String line) throws Exception {

        return switch (format) {
            case "stylish" -> Stylish.formatStylish(line);
            case "plain" -> Plain.formatPlain(line);
            case "json" -> JsonFormatter.formatJson(line);
            default -> throw new Exception("Invalid format!");
        };
    }
}
