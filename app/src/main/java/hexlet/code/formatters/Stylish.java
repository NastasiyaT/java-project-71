package hexlet.code.formatters;

import hexlet.code.Item;

import java.util.List;
import java.util.stream.Collectors;

import static hexlet.code.Comparator.TAG_ADDED;
import static hexlet.code.Comparator.TAG_REMOVED;
import static hexlet.code.Comparator.TAG_SAME;
import static hexlet.code.Comparator.TAG_UPDATED;

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
            case TAG_ADDED -> {
                return "  + " + key + ": " + obj.getValue();
            }
            case TAG_REMOVED -> {
                return "  - " + key + ": " + obj.getValue();
            }
            case TAG_SAME -> {
                return "    " + key + ": " + obj.getValue();
            }
            case TAG_UPDATED -> {
                return "  - " + key + ": " + obj.getValueOld() + "\n"
                        + "  + " + key + ": " + obj.getValueNew();
            }
            default -> throw new RuntimeException();
        }
    }
}
