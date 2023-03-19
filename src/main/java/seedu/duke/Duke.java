package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandParser;
import seedu.duke.exception.BBException;

//@@author pinyoko573
public class Duke {
    private static Ui ui;
    private static Data data;

    /**
     * Main entry-point for BudgetBuddy application.
     */
    public static void main(String[] args) {
        startApplication();

        boolean isRunning = true;
        while (isRunning) {
            try {
                String input = ui.readInput();
                Command command = CommandParser.parse(input);
                command.execute(data, ui);
                if (command.isExit()) {
                    exitApplication();
                }
            } catch (BBException err) {
                ui.printErrorMessage(err.getMessage());
            }
        }
    }

    /**
     * Initialises the ui and attempts to import the data file (.json).
     * When file could not be opened, application will terminate.
     */
    private static void startApplication() {
        ui = new Ui();

        try {
            data = Data.importData();
            assert data != null : "Data is either empty initialized or loaded with data";
        } catch (BBException err) {
            ui.printErrorMessage(err.getMessage());
            System.exit(1);
        } finally {
            ui.printLogo();
            ui.greetUser();
        }
    }

    /**
     * Exits the application.
     */
    private static void exitApplication() {
        ui.byeUser();
        System.exit(0);
    }
}

