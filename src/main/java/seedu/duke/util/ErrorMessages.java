package seedu.duke.util;

public enum ErrorMessages {
    ERROR_INVALID_COMMAND("Invalid command. Use help for the list of commands."),
    ERROR_UNKNOWN_INVALID_ACTION("Invalid action for unknown command."),
    ERROR_UNKNOWN_INVALID_ACTION_EXECUTE("Invalid execution for unknown action."),

    ERROR_BUDGET_INVALID_ACTION("Invalid action for budget. Use these actions: add, set, del, list, find, help"),
    ERROR_BUDGET_ADD_INVALID_PARAM("Invalid parameters. Example: budget add /c transport /l 3000.00"),
    ERROR_BUDGET_SET_INVALID_PARAM("Invalid parameters. Example: budget set /c transport /l 5000.00"),
    ERROR_BUDGET_DEL_INVALID_PARAM("Invalid parameters. Example: budget del /c transport"),
    ERROR_BUDGET_FIND_INVALID_PARAM("Invalid parameters. Example: budget find /c transport"),
    ERROR_BUDGET_LIST_INVALID_MONTHYEAR("Please input a valid month or month & date that is before today."),

    ERROR_DEPOSIT_INVALID_ACTION("Invalid action for deposit. Use these actions: add, del, list, clear, help"),
    ERROR_DEPOSIT_ADD_INVALID_PARAM("Invalid parameters. Example: deposit add /n lottery /a 3000.00 [/d] 23-03-2023"),
    ERROR_DEPOSIT_DEL_INVALID_PARAM("Invalid parameters. Example: deposit del /n 1" + 
        "\n Delete with the corresponding list number from `deposit list`."),
    ERROR_DEPOSIT_FIND_INVALID_PARAM("Invalid parameters. Example: deposit find /n lottery"),
    ERROR_DEPOSIT_LIST_INVALID_PARAM("Invalid parameters. Example: deposit list [/f] 01-01-2023 [/t] 01-03-2023"),
    ERROR_DEPOSIT_CLEAR_INVALID_PARAM("Invalid parameters. Example: deposit " +
            "clear /f 23-01-2000 /t 23-03-2023"),
    
    ERROR_EXPENSE_INVALID_ACTION("Invalid action for expense. Use these actions: add, del, list"),
    ERROR_EXPENSE_ADD_INVALID_PARAM("Invalid parameters. Example: expense add /c transport /n mrt /a 1.00 [/d] today"),
    ERROR_EXPENSE_DEL_INVALID_PARAM("Invalid parameters. Example: expense del /n mrt"),
    ERROR_EXPENSE_FIND_INVALID_PARAM("Invalid parameters. Example: expense find /n mrt"),
    ERROR_EXPENSE_LIST_INVALID_PARAM("Invalid parameters. Example: expense list [/c] transport"
        + "[/f] 01-01-2023 [/t] 01-03-2023"),
    ERROR_EXPENSE_CLEAR_INVALID_PARAM("Invalid parameters. Example: expense " +
            "clear /c transport /f 23-01-2000 /t 05-23-2023"),
    ERROR_EXPENSE_BUDGET_NOT_FOUND("Could not find a budget with the same category name."),

    ERROR_STATS_INVALID_ACTION("Invalid action for stats. Use these actions: show"),
    ERROR_STATS_SHOW_INVALID_PARAM("Invalid parameters. Example: stats show </m 3> </y 2022> </v de>"),
    ERROR_STATS_INVALID_OPTIONS("Please indicate the correct verbose options."),

    ERROR_GLOBAL_INVALID_NUMBER("Please input a valid number."),
    ERROR_GLOBAL_DATE_AFTER_TO("The from date cannot be after the to date."),
    ERROR_GLOBAL_DATE_AFTER_TODAY("You cannot input a date that is after today."),

    ERROR_FILE_NOT_FOUND("Could not read the file! BudgetBuddy will terminate unexpectedly..."),
    ERROR_FILE_EXPORT("Could not export the file! Please restart the application.");

    private final String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return Constants.ANSI_RED + errorMessage + Constants.ANSI_RESET;
    }
}
