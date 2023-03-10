package seedu.duke.util;

public enum Messages {
    LINE_DIVIDER("_______________"),
    INFO_WELCOME("Welcome to BudgetBuddy! What can I do for you?"),
    INFO_EXIT("Bye! Hope to see you soon!");

    private final String message;

    private Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
