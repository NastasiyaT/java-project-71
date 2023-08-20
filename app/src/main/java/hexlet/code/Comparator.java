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

            var newItem = new Item();

            if (isKey1 && !isKey2) {
                newItem = new ItemBuilder()
                        .withChange("removed")
                        .withKey(k)
                        .withValue(val1)
                        .build();
            } else if (!isKey1 && isKey2) {
                newItem = new ItemBuilder()
                        .withChange("added")
                        .withKey(k)
                        .withValue(val2)
                        .build();
            } else if (Objects.equals(val1, val2)) {
                newItem = new ItemBuilder()
                        .withChange("same")
                        .withKey(k)
                        .withValue(val1)
                        .build();
            } else {
                newItem = new ItemBuilder()
                        .withChange("updated")
                        .withKey(k)
                        .withValueOld(val1)
                        .withValueNew(val2)
                        .build();
            }

            results.add(newItem);
        }

        return results;
    }
}
