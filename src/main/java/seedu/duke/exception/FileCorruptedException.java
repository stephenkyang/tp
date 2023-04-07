package seedu.duke.exception;

import seedu.duke.util.ErrorMessages;

public class FileCorruptedException extends BBException {
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_FILE_CORRUPTED.toString();
    }
}
