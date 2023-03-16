package seedu.duke.util;

import java.io.IOException;
import java.time.LocalDate;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class DateAdapter extends TypeAdapter<LocalDate> {

    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {
        // out.beginObject();
        // out.name("date").value(value.toString());
        // out.endObject();
        out.value(value.toString());
    }

    @Override
    public LocalDate read(JsonReader in) throws IOException {
        // throw new UnsupportedOperationException("Unimplemented method 'read'");
        return LocalDate.parse(in.nextString());
    }
    
}
