package seedu.duke.BudgetCommandTest;

import org.junit.jupiter.api.Test;
import seedu.duke.Ui;
import seedu.duke.action.BudgetAction;
import seedu.duke.model.Budget;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author chongyongrui
 * Class to test delete budget commands using Junit tests
 */

public class DeleteBudgetTest {

    ArrayList<Budget> budgetList = new ArrayList<Budget>();


    Ui ui = new Ui();
    BudgetAction budgetAction = new BudgetAction(budgetList, ui);


    @Test
    void deleteBudget_existingBudget_success() {
        String budgetName = "transport";
        Double budgetLimit = 10.0;
        budgetAction.addBudget(budgetName, budgetLimit);
        assertEquals(budgetList.size(), 1);
        assert budgetList.size() == 1 : "budget creation error";
        budgetAction.deleteBudget("transport");
        assert budgetList.size() == 0 : "delete budget error";
        assertEquals(budgetList.size(), 0);
    }


    @Test
    void deleteBudget_invalidBudget_throwError() {
        String budgetName = "transport";
        Double budgetLimit = 10.0;
        budgetAction.addBudget(budgetName, budgetLimit);
        assertEquals(budgetList.size(), 1);
        assert budgetList.size() == 1 : "budget creation error";
        budgetAction.deleteBudget("food");
        assert budgetList.size() == 1 : "delete budget error";
        assertEquals(budgetList.size(), 1);
    }

}
