package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Comparison {
    public static List<Item> compare(Map<String, Object> list1, Map<String, Object> list2) {

        Set<String> keys = new TreeSet<>();
        keys.addAll(list1.keySet());
        keys.addAll(list2.keySet());

        List<Item> results = new ArrayList<>();

        for (String k : keys) {
            if (list1.containsKey(k) && !list2.containsKey(k)) {
                results.add(new Item("removed", k, list1.get(k)));
            } else if (!list1.containsKey(k) && list2.containsKey(k)) {
                results.add(new Item("added", k, list2.get(k)));
            } else if (Objects.equals(list1.get(k), list2.get(k))) {
                results.add(new Item("same", k, list1.get(k)));
            } else {
                results.add(new Item("updated", k, list1.get(k), list2.get(k)));
            }
        }

        return results;
    }
}
