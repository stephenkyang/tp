package seedu.duke.action;

import seedu.duke.model.Budget;

import java.util.ArrayList;


/**
 * Contains User Interface text responses when a budget method is run
 */
public class BudgetUIResponse {

    public static void budgetDoesNotExist() {
        System.out.println("A budget with the same name exists!");
    }


    public static void successfulBudgetDelete(String budgetName) {
        System.out.println("Successfully deleted " + budgetName);
    }

    public static void numberOfBudgets(ArrayList<Budget> budgets) {
        System.out.println("There are now " + budgets.size() + " budget(s) left");
    }


    public static void successfulBudgetAdd(String budgetName, double budgetLimit) {
        System.out.println("Successfully added " + budgetName + " with limit of $" + budgetLimit);
    }
}
