package hexlet.code;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    private String change;
    private String key;
    private Object value;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Item(@JsonProperty("change") String change,
                @JsonProperty("key") String key,
                @JsonProperty("value") Object value) {
        this.change = change;
        this.key = key;
        this.value = value;
    }

    @JsonProperty("change")
    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @JsonProperty("value")
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
