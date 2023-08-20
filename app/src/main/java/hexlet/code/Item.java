package hexlet.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@Builder(setterPrefix = "with")
@ToString(includeFieldNames = false)
public final class Item {
    private String change;
    private String key;
    private Object value;
    private Object valueOld;
    private Object valueNew;
}
