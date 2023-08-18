package hexlet.code.formatters;

import hexlet.code.Item;

import java.util.List;
import java.util.stream.Collectors;

public final class Stylish implements Format {
    public static final String STYLISH_NAME = "stylish";

    public String format(List<Item> items) {

        String output = items.stream()
                .map(Stylish::getLine)
                .collect(Collectors.joining("\n"));

        return "{\n" + output + "\n}";
    }

    private static String getLine(Item obj) {
        String diff = obj.getChange();
        String key = obj.getKey();

        switch (diff) {
            case "added" -> {
                return "  + " + key + ": " + obj.getValue();
            }
            case "removed" -> {
                return "  - " + key + ": " + obj.getValue();
            }
            case "same" -> {
                return "    " + key + ": " + obj.getValue();
            }
            case "updated" -> {
                return "  - " + key + ": " + obj.getValueOld() + "\n"
                        + "  + " + key + ": " + obj.getValueNew();
            }
            default -> throw new RuntimeException();
        }
    }
}
