package hexlet.code;

public class ItemBuilder {
    private String change = "";
    private String key = "";
    private Object value = null;
    private Object valueOld = null;
    private Object valueNew = null;

    public ItemBuilder withChange(String line) {
        this.change = line;
        return this;
    }

    public ItemBuilder withKey(String clue) {
        this.key = clue;
        return this;
    }

    public ItemBuilder withValue(Object val) {
        this.value = val;
        return this;
    }

    public ItemBuilder withValueOld(Object oldValue) {
        this.valueOld = oldValue;
        return this;
    }

    public ItemBuilder withValueNew(Object newValue) {
        this.valueNew = newValue;
        return this;
    }

    public Item build() {
        return new Item(change, key, value, valueOld, valueNew);
    }
}
