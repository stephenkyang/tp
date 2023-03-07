package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandParser;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Ui ui = new Ui();
        boolean isRunning = true;

        Ui.greetUser();

        while (isRunning) {
            String input = ui.readInput();
            Command command = CommandParser.parse(input);
            command.execute();
        }
    }
}
