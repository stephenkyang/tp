package seedu.duke.expenses;

public class CommandAddExpense extends CommandExpenses {

    /**
     * Adds an expense to the expense list
     *
     * @param list      list of expenses so far
     * @param input     input string from user
     */
    @Override
    public void doCommand(ExpenseList list, String input) {
        try {
            String[] arr = input.split(" ", 3); //remove 'expense' and 'add'
            String[] actual = arr[2].split("/"); // splits into /c, /n, /a, /d
            String category = actual[0].substring(1).trim();
            String name = actual[1].substring(1).trim();
            String date = "";
            int amount = Integer.parseInt(actual[2].substring(1).trim());
            if (actual.length > 3) {
                date = actual[3].substring(1).trim();
            }
            Expenses expense = new Expenses(category, name, amount, date);
            list.addExpense(expense);
            System.out.println("Got it, I have added " + name + " into your expenses!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("This is an invalid input!");
        }
    }
}
