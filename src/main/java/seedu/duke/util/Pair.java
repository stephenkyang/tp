package seedu.duke.util;

//@@author pinyoko573
/**
 * Custom pair data type for parsing the parameter
 * for each command.
 */
public class Pair {
    private final String key;
    private final Class<?> value;

    public Pair(String key, Class<?> value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Gets the name of the param (e.g. "/c").
     * 
     * @return the name of the param
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets the class of the param (e.g. int.class).
     * 
     * @return the class of the param
     */
    public Class<?> getValue() {
        return value;
    }
}
