package hexlet.code;

public final class Item {
    private String change;
    private String key;
    private Object value;
    private Object valueOld;
    private Object valueNew;

    public Item(String change, String key, Object value) {
        this.change = change;
        this.key = key;
        this.value = value;
    }

    public Item(String change, String key, Object valueOld, Object valueNew) {
        this.change = change;
        this.key = key;
        this.valueOld = valueOld;
        this.valueNew = valueNew;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValueNew() {
        return valueNew;
    }

    public void setValueNew(Object valueNew) {
        this.valueNew = valueNew;
    }

    public Object getValueOld() {
        return valueOld;
    }

    public void setValueOld(Object valueOld) {
        this.valueOld = valueOld;
    }
}
