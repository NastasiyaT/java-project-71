package hexlet.code.formatters;

import hexlet.code.Item;

import java.util.List;
import java.util.stream.Collectors;

public class Stylish {

    public static String formatStylish(List<Item> items) {

        String output = items.stream()
                .map(i -> {
                    try {
                        return getLine(i);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.joining("\n"));

        return "{\n" + output + "\n}";
    }

    private static String getLine(Item obj) throws Exception {
        String diff = obj.getChange();
        String key = obj.getKey();
        String result = "";

        switch (diff) {
            case "added" -> result = "  + " + key + ": " + obj.getValue();
            case "removed" -> result = "  - " + key + ": " + obj.getValue();
            case "same" -> result = "    " + key + ": " + obj.getValue();
            case "updated" -> result = "  - " + key + ": " + obj.getValueOld() + "\n" +
                    "  + " + key + ": " + obj.getValueNew();
            default -> throw new Exception("Invalid value!");
        }

        return result;
    }
}
