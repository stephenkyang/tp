package seedu.duke.exception;

import seedu.duke.util.ErrorMessages;

//@@author pinyoko573
public class FileCorruptedException extends BBException {
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_FILE_CORRUPTED.toString();
    }
}
