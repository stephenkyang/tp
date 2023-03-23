package seedu.duke.util;

import java.io.IOException;
import java.time.LocalDate;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

//@@author pinyoko573
/**
 * Gson adapter for the variable type LocalDate.
 */
public class LocalDateAdapter extends TypeAdapter<LocalDate> {
    /**
     * Tells JsonWriter the correct way to format when
     * writing the LocalDate variable.
     * 
     * @param out JsonWriter that will write to file in JSON format
     * @param date date that comes from the classes in model
     */
    @Override
    public void write(JsonWriter out, LocalDate date) throws IOException {
        out.value(date.format(Constants.ACCEPTABLE_DATE_FORMAT));
    }

    /**
     * Tells JsonReader the way to parse LocalData
     * when reading the file.
     * 
     * @return in JsonReader that outputs from the file
     */
    @Override
    public LocalDate read(JsonReader in) throws IOException {
        return LocalDate.parse(in.nextString(), Constants.ACCEPTABLE_DATE_FORMAT);
    }
    
}
