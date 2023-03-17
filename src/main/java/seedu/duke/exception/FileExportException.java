package seedu.duke.exception;

import seedu.duke.util.ErrorMessages;

//@@author pinyoko573
public class FileExportException extends BBException {
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_FILE_EXPORT.toString();
    }
}
