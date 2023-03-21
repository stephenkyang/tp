package seedu.duke.exception;

import seedu.duke.util.ErrorMessages;

public class ExpenseBudgetNotFoundException extends BBException {
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_EXPENSE_BUDGET_NOT_FOUND.toString();
    }
}
