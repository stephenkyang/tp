package seedu.duke;

import java.util.Scanner;



public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new BudgetParser();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        int i = 0;
        String command = "hi";
        while (!command.equals("bye")) {
            command = readTask();
            BudgetParser.respondToBudgetInput(command);
            i++;
        }

    }


    public static String readTask() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;

    }
}

