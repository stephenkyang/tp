package seedu.duke.util;

public enum Constants {
    // DATA FILE NAME USED FOR APPLIATION. default: data.json
    FILE_NAME("data.json"),

    // ACCEPTABLE DATE FORMAT FOR DEPOSIT AND EXPENSE. default: dd-MM-yyyy
    ACCEPTABLE_DATE_FORMAT("dd-MM-yyyy"),

    // OUTPUT DATE FORMAT FOR DISPLAYING DATE. default: dd MMM yyyy
    OUTPUT_DATE_FORMAT("dd MMM yyyy");

    private final String key;

    private Constants(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }
}
