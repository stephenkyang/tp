package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandParser;
import seedu.duke.exception.BBException;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Data data = new Data();
        Ui ui = new Ui();

        ui.greetUser();

        boolean isRunning = true;
        while (isRunning) {
            try {
                String input = ui.readInput();
                Command command = CommandParser.parse(input);
                command.execute(data);
                if (command.isExit()) {
                    ui.byeUser();
                    System.exit(0);
                }
            } catch (BBException err) {
                ui.printErrorMessage(err.getMessage());
            }
        }
    }
}

