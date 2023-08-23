package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Comparator {

    public static final String TAG_ADDED = "added";
    public static final String TAG_REMOVED = "removed";
    public static final String TAG_SAME = "same";
    public static final String TAG_UPDATED = "updated";

    public static List<Item> compare(Map<String, Object> fileContent1, Map<String, Object> fileContent2) {

        Set<String> keys = new TreeSet<>();
        keys.addAll(fileContent1.keySet());
        keys.addAll(fileContent2.keySet());

        List<Item> results = new ArrayList<>();

        for (String k : keys) {
            Item newEntry = addNewItem(k, fileContent1, fileContent2);
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
            return Item.builder()
                    .withChange(TAG_REMOVED)
                    .withKey(key)
                    .withValue(val1)
                    .build();
        } else if (caseAdded) {
            return Item.builder()
                    .withChange(TAG_ADDED)
                    .withKey(key)
                    .withValue(val2)
                    .build();
        } else if (caseEqual) {
            return Item.builder()
                    .withChange(TAG_SAME)
                    .withKey(key)
                    .withValue(val1)
                    .build();
        } else {
            return Item.builder()
                    .withChange(TAG_UPDATED)
                    .withKey(key)
                    .withValueOld(val1)
                    .withValueNew(val2)
                    .build();
        }
    }
}
