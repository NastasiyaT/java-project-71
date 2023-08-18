package hexlet.code.formatters;

import hexlet.code.Item;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Plain implements Format {
    public static final String PLAIN_NAME = "plain";

    public String format(List<Item> items) {

        return items.stream()
                .filter(i -> !i.getChange().equals("same"))
                .map(Plain::getLine)
                .collect(Collectors.joining("\n"));
    }

    private static String modifyValue(Object val) {
        if (val == null) {
            return "null";
        } else if (val instanceof List<?> || val instanceof Map<?, ?>) {
            return "[complex value]";
        } else if (val instanceof String) {
            return "'" + val + "'";
        } else {
            return val.toString();
        }
    }

    private static String getLine(Item obj) {
        String diff = obj.getChange();
        String key = obj.getKey();

        switch (diff) {
            case "added" -> {
                Object a = obj.getValue();
                return "Property '" + key + "' was added with value: " + modifyValue(a);
            }
            case "removed" -> {
                return  "Property '" + key + "' was removed";
            }
            case "updated" -> {
                Object o = obj.getValueOld();
                Object n = obj.getValueNew();
                return  "Property '" + key + "' was updated. From "
                        + modifyValue(o) + " to " + modifyValue(n);
            }
            default -> throw new RuntimeException();
        }
    }
}
