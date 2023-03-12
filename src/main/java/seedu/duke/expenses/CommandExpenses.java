package seedu.duke.expenses;

public abstract class CommandExpenses {

    /**
     * Constructor for CommandExpenses, to be overidden by subclasses
     *
     * @param list      list of expenses so far
     * @param input     input string from user
     */
    public abstract void doCommand(ExpenseList list, String input);
}
