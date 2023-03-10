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
    void createBudget_duplicateBudget_noError() {
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
}