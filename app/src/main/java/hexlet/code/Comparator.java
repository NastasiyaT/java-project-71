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
            Item newEntry = addNewItem(k, list1, list2);
            results.add(newEntry);
        }

        return results;
    }

    private static Item addNewItem(String key, Map<String, Object> items1, Map<String, Object> items2) {
        Object val1 = items1.get(key);
        Object val2 = items2.get(key);

        boolean caseRemoved = items1.containsKey(key) && !items2.containsKey(key);
        boolean caseAdded = !items1.containsKey(key) && items2.containsKey(key);
        boolean caseEqual = Objects.equals(val1, val2);

        if (caseRemoved) {
            return new ItemBuilder()
                    .withChange("removed")
                    .withKey(key)
                    .withValue(val1)
                    .build();
        } else if (caseAdded) {
            return new ItemBuilder()
                    .withChange("added")
                    .withKey(key)
                    .withValue(val2)
                    .build();
        } else if (caseEqual) {
            return new ItemBuilder()
                    .withChange("same")
                    .withKey(key)
                    .withValue(val1)
                    .build();
        } else {
            return new ItemBuilder()
                    .withChange("updated")
                    .withKey(key)
                    .withValueOld(val1)
                    .withValueNew(val2)
                    .build();
        }
    }
}
