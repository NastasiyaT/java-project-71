package hexlet.code.formatters;

import hexlet.code.Item;

import java.util.List;

public interface Format {
    String format(List<Item> items) throws Exception;
}
