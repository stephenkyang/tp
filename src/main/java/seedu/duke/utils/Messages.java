package seedu.duke.utils;

public enum Messages {
    LINE_DIVIDER("_______________"),
    INFO_WELCOME("Welcome to BudgetBuddy!");

    private final String message;

    private Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
