package seedu.duke.util;

public enum Messages {
    LINE_DIVIDER("_______________"),
    INFO_WELCOME("Welcome to BudgetBuddy! What can I do for you?"),
    INFO_EXIT("Bye! Hope to see you soon!"),

    DEPOSIT_PRINT("Here are your additional deposits: "),
    DEPOSIT_PRINT_LINE("%d. %s (%.2f)");

    private final String message;

    private Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
