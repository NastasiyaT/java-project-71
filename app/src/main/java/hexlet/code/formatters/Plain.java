package hexlet.code.formatters;

import com.fasterxml.jackson.core.type.TypeReference;
import hexlet.code.Item;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Plain {
    public static String formatPlain(String data) {

        List<Item> items = JsonDeserializer.fromJSON(new TypeReference<>() { }, data);

        Map<String, Object> plus = items.stream()
                .filter(i -> i.getChange().equals("yes"))
                .peek(m -> {
                    Object val = m.getValue();

                    if (val == null) {
                        m.setValue("null");
                    } else if (val.toString().startsWith("[") && val.toString().endsWith("]")) {
                        m.setValue("[complex value]");
                    } else if (val.toString().startsWith("{") && val.toString().endsWith("}")) {
                        m.setValue("[complex value]");
                    } else if (m.getValue().getClass().equals(String.class)) {
                        m.setValue("'" + m.getValue() + "'");
                    }
                })
                .collect(Collectors.toMap(Item::getKey, Item::getValue));

        Map<String, Object> minus = items.stream()
                .filter(i -> i.getChange().equals("no"))
                .peek(m -> {
                    Object val = m.getValue();

                    if (val == null) {
                        m.setValue("null");
                    } else if (val.toString().startsWith("[") && val.toString().endsWith("]")) {
                        m.setValue("[complex value]");
                    } else if (val.toString().startsWith("{") && val.toString().endsWith("}")) {
                        m.setValue("[complex value]");
                    } else if (m.getValue().getClass().equals(String.class)) {
                        m.setValue("'" + m.getValue() + "'");
                    }
                })
                .collect(Collectors.toMap(Item::getKey, Item::getValue));

        Set<String> keys = new TreeSet<>();
        keys.addAll(plus.keySet());
        keys.addAll(minus.keySet());

        StringBuilder str = new StringBuilder();

        for (String s : keys) {
            if (plus.containsKey(s) && !minus.containsKey(s)) {
                str.append("Property '").append(s).append("' was added with value: ").append(plus.get(s)).append("\n");
            } else if (!plus.containsKey(s) && minus.containsKey(s)) {
                str.append("Property '").append(s).append("' was removed\n");
            } else {
                str.append("Property '").append(s).append("' was updated. From ").append(minus.get(s)).append(" to ")
                        .append(plus.get(s)).append("\n");
            }
        }

        return str.substring(0, str.length() - 1);
    }
}
