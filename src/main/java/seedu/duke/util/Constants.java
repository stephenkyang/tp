package seedu.duke.util;

import java.time.format.DateTimeFormatter;

public class Constants {
    // DATA FILE NAME USED FOR APPLIATION. default: data.json
    public static final String FILE_NAME = "data.json";

    // ACCEPTABLE DATE FORMAT FOR DEPOSIT AND EXPENSE. default: dd-MM-yyyy
    public static final String ACCEPTABLE_DATE_FORMAT_STRING = "dd-MM-yyyy";
    public static final DateTimeFormatter ACCEPTABLE_DATE_FORMAT = DateTimeFormatter.ofPattern
        (ACCEPTABLE_DATE_FORMAT_STRING);

    // OUTPUT DATE FORMAT FOR DISPLAYING DATE. default: dd MMM yyyy
    public static final String OUTPUT_DATE_FORMAT_STRING = "dd MMM yyyy";
    public static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT_STRING);

    // COLOUR CODES
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";

    // BARS
    public static final int MAX_BARS = 20;
    public static final String BLACK_BAR = "\u2588";
    public static final String WHITE_BAR = "\u2591";
}
