package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {

    public static final String TYPE_JSON = "json";
    public static final String TYPE_YML1 = "yml";
    public static final String TYPE_YML2 = "yaml";

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();

        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);

        String type1 = getType(filepath1);
        String type2 = getType(filepath2);

        Map<String, Object> info1 = Parser.parseData(content1, type1);
        Map<String, Object> info2 = Parser.parseData(content2, type2);

        List<Item> data = Comparator.compare(info1, info2);

        return Formatter.chooseFormat(format, data);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    private static String getType(String filepath) {
        String format = "";

        if (filepath.endsWith(TYPE_JSON)) {
            format = TYPE_JSON;
        } else if (filepath.endsWith(TYPE_YML1) || filepath.endsWith(TYPE_YML2)) {
            format = TYPE_YML1;
        } else {
            throw new Error("Invalid type of file!");
        }

        return format;
    }
}
