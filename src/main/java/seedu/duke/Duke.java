package seedu.duke;

import java.util.ArrayList;
import java.util.logging.LogManager;

import seedu.duke.action.BudgetAction;
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

        while (true) {
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
        // Do not comment this in production stage
        LogManager logManager = LogManager.getLogManager();
        logManager.reset();

        ui = new Ui();

        try {
            data = Data.importData();
            assert data != null : "Data is either empty initialized or loaded with data";
        } catch (BBException err) {
            ui.printErrorMessage(err.getMessage());
            System.exit(1);
        } finally {
            ArrayList<String> summaryMsgs = BudgetAction.summaryBudget(data.getBudgets(), data.getExpenses());
            ui.greetUser(summaryMsgs);
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

