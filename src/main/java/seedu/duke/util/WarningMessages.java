package seedu.duke.util;

public enum WarningMessages {
    WARNING_FILE_NOT_FOUND("Could not locate data.json file! Creating a new file...");

    private String warningMessage;

    WarningMessages(String warningMessage) {
        this.warningMessage = warningMessage;
    }

    @Override
    public String toString() {
        return Constants.ANSI_YELLOW + warningMessage + Constants.ANSI_RESET;
    }
}
