package hexlet.code.formatters;

import com.fasterxml.jackson.core.type.TypeReference;
import hexlet.code.Item;

import java.util.List;

public class Stylish {

    public static String formatStylish(String data) throws Exception {

        List<Item> items = JsonDeserializer.fromJSON(new TypeReference<>() { }, data);

        StringBuilder str = new StringBuilder();
        str.append("{\n");

        for (Item i : items) {
            str.append("  ");

            switch (i.getChange()) {
                case "yes" -> str.append("+ ");
                case "no" -> str.append("- ");
                case "same" -> str.append("  ");
                default -> throw new Exception("No relevant value!");
            }

            str.append(i.getKey()).append(": ").append(i.getValue()).append("\n");
        }

        str.append("}\n");

        return str.toString();
    }
}
