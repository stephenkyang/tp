package seedu.duke.comparator;

import seedu.duke.model.Budget;

import java.util.Comparator;

//@@author chongyongrui
public class CustomBudgetLimitComparator implements Comparator<Budget> {
    public int compare(Budget budget1, Budget budget2) {
        return (int) ((budget2.getAmount() * 100) - (budget1.getAmount() * 100));
    }
}

