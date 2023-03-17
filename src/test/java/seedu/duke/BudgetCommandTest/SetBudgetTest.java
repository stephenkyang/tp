package seedu.duke.BudgetCommandTest;

import org.junit.jupiter.api.Test;
import seedu.duke.Ui;
import seedu.duke.action.BudgetAction;
import seedu.duke.model.Budget;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author chongyongrui
 * Class to test set budget commands using Junit tests
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
        assertEquals(budgetList.get(0).getAmount(), 123.0);
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
        assertEquals(budgetList.get(0).getAmount(), 10.0);
        assert (budgetList.get(0).getAmount() == 10.0) : "set new budget limit error";

    }
}
