package seedu.duke.exception;

import seedu.duke.util.ErrorMessages;

public class GlobalInvalidNumberException extends BBException {
    
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_GLOBAL_INVALID_NUMBER.toString();
    }
}
