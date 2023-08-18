package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Comparator {
    public static List<Item> compare(Map<String, Object> list1, Map<String, Object> list2) {

        Set<String> keys = new TreeSet<>();
        keys.addAll(list1.keySet());
        keys.addAll(list2.keySet());

        List<Item> results = new ArrayList<>();

        for (String k : keys) {
            Object val1 = list1.get(k);
            Object val2 = list2.get(k);

            boolean isKey1 = list1.containsKey(k);
            boolean isKey2 = list2.containsKey(k);

            if (isKey1 && !isKey2) {
                results.add(new Item("removed", k, val1));
            } else if (!isKey1 && isKey2) {
                results.add(new Item("added", k, val2));
            } else if (Objects.equals(val1, val2)) {
                results.add(new Item("same", k, val1));
            } else {
                results.add(new Item("updated", k, val1, val2));
            }
        }

        return results;
    }
}
