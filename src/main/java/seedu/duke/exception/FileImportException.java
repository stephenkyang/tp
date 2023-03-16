package seedu.duke.exception;

import seedu.duke.util.ErrorMessages;

//@@author pinyoko573
public class FileImportException extends BBException {
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_FILE_NOT_FOUND.toString();
    }
}
