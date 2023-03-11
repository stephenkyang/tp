package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import seedu.duke.action.BudgetAction;
import seedu.duke.model.Budget;

class BudgetCommandTest {
    ArrayList<Budget> budgetList = new ArrayList<Budget>();
    BudgetAction budgetAction = new BudgetAction(budgetList);

    @Test
    void createBudget_newBudget_success() {
        // Create new budget category
        String budgetName = "transport";
        Double budgetLimit = 10.0;
        budgetAction.addBudget(budgetName, budgetLimit);
        assertEquals(budgetList.size(), 1);

        budgetName = "food";
        budgetLimit = 123.0;
        budgetAction.addBudget(budgetName, budgetLimit);
        assertEquals(budgetList.size(), 2);

        assertEquals(budgetList, budgetAction.getBudgets());
    }

    @Test
    void deleteBudget_newBudget_success() {
        // Create new budget category
        String budgetName = "transport";
        Double budgetLimit = 10.0;
        budgetAction.addBudget(budgetName, budgetLimit);
        assertEquals(budgetList.size(), 1);

        budgetName = "food";
        budgetLimit = 123.0;
        budgetAction.addBudget(budgetName, budgetLimit);
        assertEquals(budgetList.size(), 2);

        assertEquals(budgetList, budgetAction.getBudgets());
    }

    @Test
    void deleteBudget_invalidBudget_throwError() {
        // Create new budget category
        String budgetName = "transport";
        Double budgetLimit = 10.0;
        budgetAction.addBudget(budgetName, budgetLimit);
        assertEquals(budgetList.size(), 1);

        budgetName = "food";
        budgetLimit = 123.0;
        budgetAction.addBudget(budgetName, budgetLimit);
        assertEquals(budgetList.size(), 2);
        budgetAction.deleteBudget("transportation");

        assertEquals(budgetList, budgetAction.getBudgets());
    }

    @Test
    void createBudget_duplicateBudget_throwError() {
        // Create new budget category
        String budgetName = "transport";
        Double budgetLimit = 10.0;
        budgetAction.addBudget(budgetName, budgetLimit);

        // Test for creating duplicate budget category
        String duplicateBudgetName = "transport";
        Double duplicateBudgetLimit = 20.0;
        budgetAction.addBudget(duplicateBudgetName, duplicateBudgetLimit);
        assertEquals(budgetList.size(), 1);
    }

    @Test
    void createBudget_sharedKeyword_noError() {
        // Create new budget category
        String budgetName = "taxi fares";
        Double budgetLimit = 100.0;
        budgetAction.addBudget(budgetName, budgetLimit);

        // Test for creating duplicate budget category
        String duplicateBudgetName = "bus fares";
        Double duplicateBudgetLimit = 200.0;
        budgetAction.addBudget(duplicateBudgetName, duplicateBudgetLimit);
        assertEquals(budgetList.size(), 2);
    }
}
