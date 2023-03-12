package seedu.duke;

import java.util.ArrayList;

import seedu.duke.model.Budget;
import seedu.duke.model.Deposit;

public class Data {
    public ArrayList<Budget> budgetList;
    public ArrayList<Deposit> depositList;

    // create load and save methods here
    public Data() {
        budgetList = new ArrayList<Budget>();
        depositList = new ArrayList<Deposit>();
    }
}
