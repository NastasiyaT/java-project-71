package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {

        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();

        if (!Files.exists(path1)) {
            throw new Exception("File " + filepath1 + " doesn't exist!");
        } else if (!Files.exists(path2)) {
            throw new Exception("File " + filepath2 + " doesn't exist!");
        }

        Map<String, Object> list1 = Parser.parseFile(path1.toString());
        Map<String, Object> list2 = Parser.parseFile(path2.toString());

        Set<String> keys = new TreeSet<>();
        keys.addAll(list1.keySet());
        keys.addAll(list2.keySet());

        List<Item> results = new ArrayList<>();

        for (String k : keys) {
            if (list1.containsKey(k) && !list2.containsKey(k)) {
                results.add(new Item("no", k, list1.get(k)));
            } else if (!list1.containsKey(k) && list2.containsKey(k)) {
                results.add(new Item("yes", k, list2.get(k)));
            } else if (Objects.equals(list1.get(k), list2.get(k))) {
                results.add(new Item("same", k, list1.get(k)));
            } else {
                results.add(new Item("no", k, list1.get(k)));
                results.add(new Item("yes", k, list2.get(k)));
            }
        }

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(results);
    }
}
