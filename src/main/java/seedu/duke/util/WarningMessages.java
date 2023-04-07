package seedu.duke.util;

public enum WarningMessages {
    WARNING_FILE_NOT_FOUND("Could not locate data.json file. A file will be automatically created after an action!");

    private final String warningMessage;

    private WarningMessages(String warningMessage) {
        this.warningMessage = warningMessage;
    }

    @Override
    public String toString() {
        return warningMessage;
    }
}
