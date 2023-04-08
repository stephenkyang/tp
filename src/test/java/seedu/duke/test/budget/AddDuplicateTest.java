package seedu.duke.test.budget;

import org.junit.jupiter.api.Test;
import seedu.duke.Ui;
import seedu.duke.action.BudgetAction;
import seedu.duke.model.Budget;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddDuplicateTest {
    ArrayList<Budget> budgetList = new ArrayList<Budget>();


    Ui ui = new Ui();
    BudgetAction budgetAction = new BudgetAction(budgetList, ui);
    @Test
    void addDuplicateFail() {
        String budgetName = "transport";
        double budgetLimit = 10.0;
        budgetAction.addBudget(budgetName, budgetLimit);
        assertEquals(budgetList.size(), 1);
        assert budgetList.size() == 1 : "first budget creation error";
        budgetAction.addBudget(budgetName, budgetLimit);
        assertEquals(budgetList.size(), 1);
        assert budgetList.size() == 1 : "second budget creation error";
        assertEquals(budgetList, budgetAction.getBudgets());
    }
}
