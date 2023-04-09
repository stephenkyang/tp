package seedu.duke.test.budget;

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
        assertEquals(2, budgetList.size());
        assert budgetList.size() == 2 : "second budget creation error";
        assertEquals(budgetList, budgetAction.getBudgets());
    }

    @Test
    void addBudget_negativeBudgetLimit_throwError() {
        budgetList.clear();
        String budgetName = "transport";
        Double budgetLimit = -10.0;
        try {
            budgetAction.addBudget(budgetName, budgetLimit);
        } catch (AssertionError e) {
            System.out.println("Invalid input");
        }
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
        assertEquals(1, budgetList.size());

    }

    @Test
    void addBudget_nullBudgetLimit_throwError() {

        budgetList.clear();
        String budgetName = "test";
        Double budgetLimit = null;
        try {
            budgetAction.addBudget(budgetName, budgetLimit);
        } catch (NullPointerException e) {
            System.out.println("Invalid input");
        }

        assertEquals(0, budgetList.size());

    }


    @Test
    void addBudget_emojiBudget_success() {

        budgetList.clear();
        String budgetName = "\uD83D\uDE1C";
        Double budgetLimit = 100.0;
        try {
            budgetAction.addBudget(budgetName, budgetLimit);
        } catch (NullPointerException e) {
            System.out.println("Invalid input");
        }

        assertEquals(1, budgetList.size());

    }


}
