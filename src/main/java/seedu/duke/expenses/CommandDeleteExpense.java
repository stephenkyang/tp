package seedu.duke.expenses;

public class CommandDeleteExpense extends CommandExpenses {

    /**
     * Deletes an expense according to the index of the expense in the list
     *
     * @param list      list of expenses so far
     * @param input     input string from user
     */
    @Override
    public void doCommand(ExpenseList list, String input) {
        String[] arr = input.split(" ");
        int index = Integer.parseInt(arr[2]);
        if (arr.length >= 3) {
            String entry = list.getExpense(index).toString().trim();
            list.delete(index);
            System.out.println("Okay, " + entry + " has been deleted!");
            System.out.println("Now there are " + list.getSize() + " expenses in your list!");
        } else {
            System.out.println("The index cannot be left blank!");
        }
    }
}
