package hexlet.code.formatters;

import hexlet.code.Item;

import java.util.List;
import java.util.stream.Collectors;

public class Plain {
    public static String formatPlain(List<Item> items) {

        return items.stream()
                .filter(i -> !i.getChange().equals("same"))
                .peek(k -> {
                    if (k.getChange().equals("updated")) {
                        k.setValueNew(modifyValue(k.getValueNew()));
                        k.setValueOld(modifyValue(k.getValueOld()));
                    } else {
                        k.setValue(modifyValue(k.getValue()));
                    }
                })
                .map(m -> {
                    try {
                        return getLine(m);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.joining("\n"));
    }

    private static String modifyValue(Object val) {
        String result = "";

        if (val == null) {
            result = "null";
        } else if (val.toString().startsWith("[") && val.toString().endsWith("]")) {
            result = "[complex value]";
        } else if (val.toString().startsWith("{") && val.toString().endsWith("}")) {
            result = "[complex value]";
        } else if (val.getClass().equals(String.class)) {
            result = "'" + val + "'";
        } else {
            result = val.toString();
        }

        return result;
    }

    private static String getLine(Item obj) throws Exception {
        String diff = obj.getChange();
        String key = obj.getKey();

        switch (diff) {
            case "added" -> {
                return "Property '" + key + "' was added with value: " + obj.getValue();
            }
            case "removed" -> {
                return  "Property '" + key + "' was removed";
            }
            case "updated" -> {
                return  "Property '" + key + "' was updated. From "
                        + obj.getValueOld() + " to " + obj.getValueNew();
                }
            default -> throw new Exception("Invalid value!");
        }
    }
}
