package seedu.duke.util;

public enum Messages {
    LINE_DIVIDER("_______________"),
    INFO_WELCOME("Welcome to BudgetBuddy! What can I do for you?"),
    INFO_EXIT("Bye! Hope to see you soon!"),
    BUDGET_DOES_NOT_EXIST("This budget does not exist!"),
    BUDGET_NAME_USED("This budget name is already in use!"),
    BUDGET_ADD_SUCCESSFUL("Successfully added %s with limit of $%.2f"),
    BUDGET_DELETE_SUCCESSFUL("Sucessfully deleted %s."),
    BUDGET_PRINT_BUDGET("%d. Name: \"%s\" Limit: $%.2f"),
    BUDGET_NUMBER_OF("There are %d budget categories."),

    BUDGET_COMMANDS("\n1. You can add a monthly budget: \n" +
        "   budget add /c (category name) /l (spending limit) \n" +
        "2. You can modify an existing budget: \n" +
        "   budget set /c (category) /l (spending limit)\n" +
        "3. You can remove a monthly budget:    \n" +
        "   budget del /c (category)\n" +
        "4. You can list all budgets:  \n" +
        "   budget list \n" +
        "5. You can search for budgets that include a certain keyword:\n" +
        "   budget find /c (keyword)"),

    DEPOSIT_PRINT("Here are your additional deposits: "),
    DEPOSIT_PRINT_DEPOSIT("%d. Name: \"%s\" Amount: $%.2f");


    private final String message;

    private Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
