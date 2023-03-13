package seedu.duke;

import java.util.ArrayList;

import seedu.duke.model.Budget;
import seedu.duke.model.Deposit;
import seedu.duke.model.Expense;

public class Data {
    public ArrayList<Budget> budgetList;
    public ArrayList<Deposit> depositList;
    public ArrayList<Expense> expenseList;

    // create load and save methods here
    public Data() {
        budgetList = new ArrayList<Budget>();
        depositList = new ArrayList<Deposit>();
        expenseList = new ArrayList<Expense>();
    }
}
