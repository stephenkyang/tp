package seedu.duke.exception;

import seedu.duke.util.ErrorMessages;

//@@author pinyoko573
public class GlobalDateAfterToException extends BBException {
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_GLOBAL_DATE_AFTER_TO.toString();
    }
}
