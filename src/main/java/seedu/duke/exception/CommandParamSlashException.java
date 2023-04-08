package seedu.duke.exception;

import seedu.duke.util.ErrorMessages;

//@@author pinyoko573
public class CommandParamSlashException extends BBException {
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_INVALID_PARAM_SLASH.toString();
    }
}
