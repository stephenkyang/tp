package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandParser;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Data data = new Data();
        Ui ui = new Ui();

        Ui.greetUser();

        boolean isRunning = true;
        while (isRunning) {
            try {
                String input = ui.readInput();
                Command command = CommandParser.parse(input);
                command.execute(data);
            } catch (Exception e) {
                // handle exception here
            }
        }
    }
}

