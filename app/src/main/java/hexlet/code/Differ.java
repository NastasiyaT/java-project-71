package hexlet.code;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String path1 = Paths.get(filepath1).toAbsolutePath().normalize().toString();
        String path2 = Paths.get(filepath2).toAbsolutePath().normalize().toString();

        Map<String, Object> list1 = Parser.parseFile(path1);
        Map<String, Object> list2 = Parser.parseFile(path2);

        List<Item> data = Comparator.compare(list1, list2);

        return Formatter.chooseFormat(format, data);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
}
