package seedu.duke;

import java.util.Scanner;

import seedu.duke.util.Messages;

/**
 * Handles the user interface of the application, including user inputs and message outputs
 */
public class Ui {
    private Scanner in;
    private String input;

    public Ui() {
        in = new Scanner(System.in);
    }

    public String readInput() {
        input = in.nextLine();
        return input;
    }

    public void printMessage(String... message) {
        System.out.println(Messages.LINE_DIVIDER);
        System.out.println(message);
        System.out.println(Messages.LINE_DIVIDER);
    }

    public static void greetUser() {
        System.out.println(Messages.INFO_WELCOME);
    }
}
