package seedu.duke.exception;

import seedu.duke.util.ErrorMessages;

//@@author pinyoko573
public class CommandActionExecuteInvalidException extends BBException {
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_UNKNOWN_INVALID_ACTION_EXECUTE.toString();
    }
}
