package seedu.duke.expenses;

import seedu.duke.expenses.Expenses;

import java.util.ArrayList;

public class ExpenseList {
    private ArrayList<Expenses> e = new ArrayList<>();

    /**
     * Adds an expense to the list
     *
     * @param expense   The expense to be added
     */
    public void addExpense(Expenses expense) {
        e.add(expense);
    }

    /**
     * get method for the size of the expense list
     *
     * @return      Size of the expense list
     */
    public int getSize() {
        return e.size();
    }

    /**
     * Deletes the expense of the particular index in the list
     *
     * @param index     The index of the expense in the list to be deleted
     */
    public void delete(int index){
        e.remove(index);
    }

    /**
     * Get method for the expense in the particular index of the list
     *
     * @param index     The index of the expense of the list
     * @return          Returns the expense in that particular index
     */
    public Expenses getExpense(int index) {
        return e.get(index);
    }
}
