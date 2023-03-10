package seedu.duke.util;

public class Pair {
    private final String key;
    private final Class<?> value;

    public Pair(String key, Class<?> value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Class<?> getValue() {
        return value;
    }
}
