package hexlet.code;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();

        Map<String, Object> list1 = Parser.parseFile(path1.toString());
        Map<String, Object> list2 = Parser.parseFile(path2.toString());

        List<Item> data = Comparison.compare(list1, list2);

        return Formatter.chooseFormat(format, data);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();

        Map<String, Object> list1 = Parser.parseFile(path1.toString());
        Map<String, Object> list2 = Parser.parseFile(path2.toString());

        List<Item> data = Comparison.compare(list1, list2);

        return Formatter.chooseFormat("stylish", data);
    }
}
