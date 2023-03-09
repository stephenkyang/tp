package seedu.duke;

import java.util.ArrayList;

import seedu.duke.model.Budget;

public class Data {
    public ArrayList<Budget> budgetList;

    // create load and save methods here
    public Data() {
        budgetList = new ArrayList<Budget>();
    }
}
