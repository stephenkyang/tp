package seedu.duke.exception;

import seedu.duke.util.ErrorMessages;

public class GlobalInvalidMonthYearException extends BBException {
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_BUDGET_LIST_INVALID_MONTHYEAR.toString();
    }
}
