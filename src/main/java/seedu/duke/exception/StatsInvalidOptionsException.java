package seedu.duke.exception;

import seedu.duke.util.ErrorMessages;

//@@author pinyoko573
public class StatsInvalidOptionsException extends BBException {
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_STATS_INVALID_OPTIONS.toString();
    }
    
}
