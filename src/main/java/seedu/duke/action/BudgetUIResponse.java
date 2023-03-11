package seedu.duke.action;

import seedu.duke.model.Budget;

import java.util.ArrayList;


/**
 * Contains User Interface text responses when a budget method is run
 */
public class BudgetUIResponse {

    public static void budgetDoesNotExist() {
        System.out.println("This budget does not exist!");
    }

    public static void budgetNameUsed() {
        System.out.println("This budget name is already in use!");
    }



    public static void successfulBudgetDelete(String budgetName) {
        System.out.println("Successfully deleted " + budgetName);
    }

    public static void numberOfBudgets(ArrayList<Budget> budgets) {
        System.out.println("There are " + budgets.size() + " budget(s)");
    }


    public static void successfulBudgetAdd(String budgetName, double budgetLimit) {
        System.out.println("Successfully added " + budgetName + " with limit of $" + budgetLimit);
    }
}
