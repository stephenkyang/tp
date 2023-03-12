package seedu.duke.util;

public enum ErrorMessages {
    ERROR_INVALID_COMMAND("Invalid command."),
    ERROR_BUDGET_INVALID_ACTION("Invalid action for budget. Use these actions: add, set, del, list"),
    ERROR_UNKNOWN_INVALID_ACTION("Invalid action for unknown command."),

    ERROR_GLOBAL_INVALID_NUMBER("Please input a valid number."),

    ERROR_BUDGET_ADD_INVALID_PARAM("Invalid parameters. Example: budget add /c transport /l 3000.00"),
    ERROR_BUDGET_SET_INVALID_PARAM("Invalid parameters. Example: budget set /c transport /l 5000.00"),
    ERROR_BUDGET_DEL_INVALID_PARAM("Invalid parameters. Example: budget del /c transport");

    private final String errorMessage;

    private ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
