package seedu.duke.action;

import seedu.duke.model.Budget;

import java.util.ArrayList;

/**
 * Contains methods related to budget function
 */

public class BudgetAction {
    private ArrayList<Budget> budgets;

    public BudgetAction(ArrayList<Budget> budgets) {
        this.budgets = budgets;
    }

    public ArrayList<Budget> getBudgets() {
        return this.budgets;
    }

    /**
     * Creates a budget of name and limit determined by user input
     *
     * @param budgetName  the name of the budget that the user wants to create
     * @param budgetLimit the monetary limit of the budget
     */
    public void addBudget(String budgetName, double budgetLimit) {
        // Check if there are any duplicate budgets
        if (checkDuplicateBudget(budgetName)) {
            BudgetUIResponse.budgetNameUsed();
            return;
        }

        Budget budget = new Budget(budgetName, budgetLimit);
        budgets.add(budget);
        BudgetUIResponse.successfulBudgetAdd(budgetName, budgetLimit);
        BudgetUIResponse.numberOfBudgets(budgets);
    }

    /**
     * Deletes a budget from the budget list
     *
     * @param budgetName the name of the budget to delete
     */
    public void deleteBudget(String budgetName) {

        Budget budget = getBudget(budgetName);
        if (budget == null) {
            BudgetUIResponse.budgetDoesNotExist();
            return;
        }

        budgets.remove(budget);
        BudgetUIResponse.successfulBudgetDelete(budgetName);
        BudgetUIResponse.numberOfBudgets(budgets);
    }

    /**
     * Modifies the budget limit of a chose budget
     *
     * @param budgetName  the budget to modify the budget limit for
     * @param budgetLimit the new budget limit
     */

    public void setBudget(String budgetName, double budgetLimit) {
        Budget budget = getBudget(budgetName);
        if (budget == null) {
            BudgetUIResponse.budgetDoesNotExist();
            return;
        }

        budget.setLimit(budgetLimit);
    }

    /**
     * Finds if a budget contains the keywords input by user
     *
     * @param keyword the word the user wants to check for
     */
    public void findBudget(String keyword) {
        ArrayList<Budget> foundBudgets = new ArrayList<>();
        for (Budget budget : budgets) {

            if (budget.getName().contains(keyword)) {
                foundBudgets.add(budget);
            }
        }
        if (foundBudgets.isEmpty()) {
            BudgetUIResponse.budgetDoesNotExist();
        } else {
            int i = 1;
            for (Budget a : foundBudgets) {
                if (a != null) {
                    System.out.print(i + ". ");
                    a.printBudget();
                    i++;
                }
            }
            BudgetUIResponse.numberOfBudgets(foundBudgets);
        }
        foundBudgets.clear();
    }


    /**
     * Checks if a certain budget exists
     *
     * @param budgetName budget name to check for if it has been used
     */

    private Budget getBudget(String budgetName) {
        for (Budget budget : budgets) {
            if (budget.getName().equals(budgetName)) {
                return budget;
            }
        }

        return null;
    }

    /**
     * Checks if a certain budget name has already been used
     *
     * @param budgetName budget name to check for if it has been used
     */
    private boolean checkDuplicateBudget(String budgetName) {
        for (Budget budget : budgets) {
            if (budget.getName().equals(budgetName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prints all the details of all budgets in the list
     */
    public void printBudgets() {
        int i = 1;
        for (Budget a : budgets) {
            if (a != null) {
                System.out.print(i + ". ");
                a.printBudget();
                i++;
            }
        }
        BudgetUIResponse.numberOfBudgets(budgets);
    }

}
