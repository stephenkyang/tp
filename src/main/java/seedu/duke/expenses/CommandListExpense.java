package seedu.duke.expenses;

public class CommandListExpense extends CommandExpenses {

    /**
     * Lists out all current expenses recorded thus far
     *
     * @param list      list of expenses so far
     * @param input     input string from user
     */
    @Override
    public void doCommand(ExpenseList list, String input) {
        for (int i = 0; i < list.getSize(); i++) {
            int index = 1 + 1;
            System.out.println(index + list.getExpense(i).toString());
        }
    }
}
