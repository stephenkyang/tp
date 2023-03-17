package seedu.duke.BudgetCommandTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import seedu.duke.Ui;
import seedu.duke.action.BudgetAction;
import seedu.duke.model.Budget;

//@@author chongyongrui

/**
 * Class to test delete budget commands using Junit tests
 */

class AddCommandTest {
    ArrayList<Budget> budgetList = new ArrayList<Budget>();


    Ui ui = new Ui();
    BudgetAction budgetAction = new BudgetAction(budgetList, ui);

    @Test
    void addBudget_newBudget_success() {

        String budgetName = "transport";
        Double budgetLimit = 10.0;
        budgetAction.addBudget(budgetName, budgetLimit);
        assertEquals(budgetList.size(), 1);
        assert budgetList.size() == 1 : "first budget creation error";
        budgetName = "food";
        budgetLimit = 123.0;
        budgetAction.addBudget(budgetName, budgetLimit);
        assertEquals(budgetList.size(), 2);
        assert budgetList.size() == 2 : "second budget creation error";
        assertEquals(budgetList, budgetAction.getBudgets());
    }

    @Test
    void addBudget_duplicateBudget_throwError() {

        String budgetName = "transport";
        Double budgetLimit = 10.0;
        budgetAction.addBudget(budgetName, budgetLimit);
        String duplicateBudgetName = "transport";
        Double duplicateBudgetLimit = 20.0;
        budgetAction.addBudget(duplicateBudgetName, duplicateBudgetLimit);
        assert budgetList.size() == 1 : "budget creation error";
        assertEquals(budgetList.size(), 1);

    }
}
