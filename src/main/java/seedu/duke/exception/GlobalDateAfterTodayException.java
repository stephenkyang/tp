package seedu.duke.exception;

import seedu.duke.util.ErrorMessages;

//@@author pinyoko573
public class GlobalDateAfterTodayException extends BBException {
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_GLOBAL_DATE_AFTER_TODAY.toString();
    }
}
