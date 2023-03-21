package seedu.duke.util;

public enum Messages {
    LINE_DIVIDER("_______________"),
    APP_LOGO("\n" +
            " █▀▀█ █   █ █▀▀▄ █▀▀▀ █▀▀ ▀▀█▀▀ 　  █▀▀█ █  █ █▀▀▄ █▀▀▄ █  █ \n" +
            " █▀▀▄ █   █ █  █ █ ▀█ █▀▀   █   　  █▀▀▄ █  █ █  █ █  █ █▄▄█ \n" +
            " █▄▄█  ▀▀▀  ▀▀▀  ▀▀▀▀ ▀▀▀   ▀   　  █▄▄█  ▀▀▀ ▀▀▀  ▀▀▀  ▄▄▄█"),
    INFO_WELCOME("Welcome to BudgetBuddy! What can I do for you?"),
    INFO_EXIT("Bye! Hope to see you soon!"),

    BUDGET_DOES_NOT_EXIST("This budget does not exist!"),
    BUDGET_NAME_USED("This budget name is already in use!"),
    BUDGET_ADD_SUCCESSFUL("Successfully added %s with limit of $%.2f"),
    BUDGET_DELETE_SUCCESSFUL("Successfully deleted %s."),
    BUDGET_PRINT_BUDGET("%d. Name: \"%s\" Limit: $%.2f"),
    BUDGET_NUMBER_OF("There are %d budget categories."),
    BUDGET_HELP_COMMANDS("\n1. You can add a monthly budget: \n" +
            "   budget add /c (category name) /l (spending limit) \n" +
            "2. You can modify an existing budget: \n" +
            "   budget set /c (category) /l (spending limit)\n" +
            "3. You can remove a monthly budget:    \n" +
            "   budget del /c (category)\n" +
            "4. You can list all budgets:  \n" +
            "   budget list \n" +
            "5. You can search for budgets that include a certain keyword:\n" +
            "   budget find /c (keyword)"),

    DEPOSIT_DEPOSIT("%d. %s ($%.2f) on %s"),
    DEPOSIT_ADD_SUCCESSFUL("The following deposit has been added:"),
    DEPOSIT_DELETE_SUCCESSFUL("The following deposit has been removed:"),
    DEPOSIT_CLEAR_SUCCESSFUL("Number of items cleared: "),
    DEPOSIT_CLEAR_ZERO("No items in that range."),
    DEPOSIT_LIST_PREVIOUS("Here are your previous deposits:"),
    DEPOSIT_LIST_CURRENT("Here are your deposits for this month:"),
    DEPOSIT_LIST_RANGE_FROM("Here are your deposits from %s:"),
    DEPOSIT_LIST_RANGE_TO("Here are your deposits until %s:"),
    DEPOSIT_LIST_RANGE("Here are your deposits from %s to %s:"),
    DEPOSIT_LIST_NOTHING("You have no deposits."),
    DEPOSIT_HELP_COMMANDS("1. You can add an deposit by: \n" +
            "   deposit add /n (category name) /a (amount) [optional] /d DD-MM-YYY \n" +
            "2. You can delete a deposit:    \n" +
            "   deposit del /n (deposit number) \n" +
            "3. You can list all deposits:  \n" +
            "   deposit list \n" +
            "4. You can search for deposits that include a certain keyword:\n" +
            "   deposit find /c (keyword)"),
    // DEPOSIT_PRINT("Here are your additional deposits: "),
    // DEPOSIT_ADD_SUCCESSFUL("Successfully added %s with amount of $%.2f"),
    // DEPOSIT_DELETE_SUCCESSFUL("Successfully deleted %s with amount of $%.2f"),
    // DEPOSIT_NUMBER_OF("You have a total of %d deposits."),
    // DEPOSIT_PRINT_DEPOSIT("%d. Name: \"%s\" Amount: $%.2f"),

    EXPENSE_EXPENSE("%d. [%s] %s ($%.2f) on %s"),
    EXPENSE_ADD_SUCCESSFUL("The following expense has been added:"),
    EXPENSE_DELETE_SUCCESSFUL("The following expense has been removed:"),
    EXPENSE_LIST_ALL("all"),
    EXPENSE_LIST_PREVIOUS("Here are your previous expenses for %s:"),
    EXPENSE_LIST_CURRENT("Here are your expenses for this month for %s:"),
    EXPENSE_LIST_RANGE_FROM("Here are your expenses from %s for %s:"),
    EXPENSE_LIST_RANGE_TO("Here are your expenses until %s for %s:"),
    EXPENSE_LIST_RANGE("Here are your expenses from %s to %s for %s:"),
    EXPENSE_LIST_NOTHING("You have no expenses."),

    STATS_PRINT_INTRO("Your current progress for this month: /n"),
    STATS_PRINT_DEPOSITS("Extra deposits: "),
    STATS_PRINT_BUDGET_PROGRESS("Total budget progress: "),
    STATS_PRINT_RIGHT_TRACK("Good job! You are on the right track!"),
    STATS_PRINT_OVERSPEND("Oh no! You seem to be spending too much!"),
    STATS_PRINT_NO_STATS("There are no stats currently! Please try another command!");

    private final String message;

    private Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
