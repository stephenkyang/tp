package seedu.duke.test.budget;

import org.junit.jupiter.api.Test;
import seedu.duke.Ui;
import seedu.duke.action.BudgetAction;
import seedu.duke.model.Budget;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
//@@author chongyongrui

/**
 * class to test the set budget method
 */

public class SetBudgetTest {
    ArrayList<Budget> budgetList = new ArrayList<Budget>();


    Ui ui = new Ui();
    BudgetAction budgetAction = new BudgetAction(budgetList, ui);

    @Test
    void setBudget_validBudget_success() {

        String budgetName = "transport";
        Double budgetLimit = 10.0;
        budgetAction.addBudget(budgetName, budgetLimit);
        assertEquals(budgetList.size(), 1);
        assert budgetList.size() == 1 : "first budget creation error";
        Double newBudgetLimit = 123.0;
        budgetAction.setBudget(budgetName, newBudgetLimit);
        assertEquals(123.0, budgetList.get(0).getAmount());
        assert (budgetList.get(0).getAmount() == 123.0) : "set new budget limit error";
    }

    @Test
    void setBudget_invalidBudget_throwError() {

        String budgetName = "transport";
        Double budgetLimit = 10.0;
        budgetAction.addBudget(budgetName, budgetLimit);
        assertEquals(budgetList.size(), 1);
        assert budgetList.size() == 1 : "first budget creation error";
        String invalidBudgetName = "food";
        Double newBudgetLimit = 123.0;
        budgetAction.setBudget(invalidBudgetName, newBudgetLimit);
        assertEquals(10.0, budgetList.get(0).getAmount());
        assert (budgetList.get(0).getAmount() == 10.0) : "set new budget limit error";

    }

    @Test
    void setBudget_nullBudgetLimit_throwError() {

        budgetList.clear();
        String budgetName = "test";
        Double budgetLimit = null;
        budgetAction.addBudget(budgetName, 100);
        try {
            budgetAction.setBudget(budgetName, budgetLimit);
        } catch (NullPointerException e) {
            System.out.println("Invalid input");
        }

        assertEquals(1, budgetList.size());

    }

    @Test
    void setBudget_negativeBudgetLimit_throwError() {
        budgetList.clear();
        String budgetName = "transport";
        Double budgetLimit = -10.0;
        budgetAction.addBudget(budgetName, 10);
        assertEquals(1, budgetList.size());
        assert budgetList.size() == 1 : "budget creation error";
        try {
            budgetAction.setBudget(budgetName, budgetLimit);
        } catch (AssertionError e) {
            System.out.println("Invalid input");
        }
    }
}
