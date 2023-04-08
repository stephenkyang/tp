package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.util.Messages;

//@@author pinyoko573

/**
 * Handles the user interface of the application, including user inputs and message outputs
 */
public class Ui {
    private static Logger logger = Logger.getLogger("Message");
    
    private Scanner in;
    private String input;

    public Ui() {
        in = new Scanner(System.in);
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
        logger.log(Level.SEVERE, "Error occured.");
        printMessage(message);
    }

    /**
     * Greets the user and shows the app logo.
     * Also prints the current budget progress if there is data.
     * 
     * @param summaryMsgs messages for current budget progress
     */
    public void greetUser(ArrayList<String> summaryMsgs) {
        ArrayList<String> msgs = new ArrayList<String>();
        msgs.add(Messages.APP_LOGO.toString());
        msgs.add(Messages.INFO_WELCOME.toString());
        msgs.addAll(summaryMsgs);

        printMessage(msgs.toArray(new String[msgs.size()]));
    }

    /**
     * Say bye to the user.
     */
    public void byeUser() {
        printMessage(Messages.INFO_EXIT.toString());
    }

}
