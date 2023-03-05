package seedu.duke;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        boolean isRunning = true;

        Ui.greetUser();

        while (isRunning) {
            String input = ui.readInput();
        }

        System.exit(0);
    }
}
