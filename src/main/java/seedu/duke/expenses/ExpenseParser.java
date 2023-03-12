package seedu.duke.expenses;

public class ExpenseParser {

    public static final String ERROR1 = "You might have missed out an argument!";
    public static final String INVALID = "Oops, seems like you have entered an invalid command!";

    /**
     * Takes in the parsed user input for the expense type commands and checks which valid command to execute
     *
     * @param list      A list of all the expenses
     * @param input     Takes the user input in a String
     */
    public static void parseExpenseCommand(ExpenseList list, String input) {
        String[] arr = input.split(" ");
        if (arr.length < 2) {
            System.out.println(ERROR1);
        }
        String command = arr[1];
        switch (command) {
        case "add" -> {
            //add expense
            CommandExpenses addCommand = new CommandAddExpense();
            addCommand.doCommand(list, input);
        }
        case "list" -> {
            //list expenses
            CommandExpenses listCommand = new CommandListExpense();
            listCommand.doCommand(list, input);
        }
        case "delete" -> {
            //delete expense
            CommandExpenses deleteCommand = new CommandDeleteExpense();
            deleteCommand.doCommand(list, input);
        }
        default -> System.out.println(INVALID);
        }
    }
}
