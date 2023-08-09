package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

public class Formatter {
    public static String chooseFormat(String format, String line) throws Exception {

        String result = "";

        if (format.equals("stylish")) {
            result = Stylish.formatStylish(line);
        } else if (format.equals("plain")) {
            result = Plain.formatPlain(line);
        } else if (format.equals("json")) {
            result = JsonFormatter.formatJson(line);
        } else {
            throw new Exception("Invalid format!");
        }

        return result;
    }
}
