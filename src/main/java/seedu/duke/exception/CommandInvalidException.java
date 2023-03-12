package seedu.duke.exception;

import seedu.duke.util.ErrorMessages;

public class CommandInvalidException extends BBException {

    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_INVALID_COMMAND.toString();
    }
    
}
