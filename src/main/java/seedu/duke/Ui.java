package seedu.duke;

import java.util.Scanner;

import seedu.duke.util.Constants;
import seedu.duke.util.Messages;

//@@author pinyoko573

/**
 * Handles the user interface of the application, including user inputs and message outputs
 */
public class Ui {
    private Scanner in;
    private String input;

    public Ui() {
        in = new Scanner(System.in);
    }

    public static void printProgressBar(double ratio) {
        int numberOfBlocks = 0;
        if ((int) ratio >= 1) {
            numberOfBlocks = 20;
        } else {
            numberOfBlocks = (int) ratio;
        }
        int excess = (int) ratio / 20;
        int i = 0;
        int numberOfBlanks = 20 - numberOfBlocks;
        while (i < numberOfBlocks) {
            if (excess > 0) {
                System.out.print("█");
            } else {
                System.out.print(Constants.ANSI_RED + "█" + Constants.ANSI_RESET);
            }
            i++;
        }
        i = 0;
        while (i < numberOfBlanks) {
            System.out.print("░");
            i++;
        }
        System.out.println(" ");
        if (ratio >= 1) {
            System.out.println("You have exceeded the budget!");
        }


    }


    /**
     * Reads the input from IO.
     *
     * @return the string of the input
     */

    public String readInput() {
        input = in.nextLine();
        return input;
    }

    /**
     * Print messages that are enclosed in a line divider.
     *
     * @param messages messages that to be printed.
     */
    public void printMessage(String... messages) {
        System.out.println(Messages.LINE_DIVIDER);
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(Messages.LINE_DIVIDER);
    }

    /**
     * Print error messages.
     *
     * @param message the error message.
     */
    public void printErrorMessage(String message) {
        printMessage(message);
    }

    /**
     * Greets the user.
     */
    public void greetUser() {
        printMessage(Messages.INFO_WELCOME.toString());
    }

    /**
     * Say bye to the user.
     */
    public void byeUser() {
        printMessage(Messages.INFO_EXIT.toString());
    }
}
