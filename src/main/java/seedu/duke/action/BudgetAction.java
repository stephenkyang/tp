package seedu.duke.action;

import seedu.duke.Ui;
import seedu.duke.model.Budget;
import seedu.duke.model.Expense;

import java.util.ArrayList;
import java.util.Objects;

import static seedu.duke.action.ExpenseAction.expenses;

/**
 * Contains methods related to budget function
 */

public class BudgetAction {
    private static ArrayList<Budget> budgets;
    private static BudgetUIResponse budgetUi;

    public BudgetAction(ArrayList<Budget> budgets, Ui ui) {
        this.budgets = budgets;
        budgetUi = new BudgetUIResponse(ui);
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
            budgetUi.printBudgetNameUsed();
            return;
        }

        Budget budget = new Budget(budgetName, budgetLimit);
        budgets.add(budget);

        budgetUi.printBudgetAddSuccessful(budget, budgets.size());

    }

    /**
     * Deletes a budget from the budget list
     *
     * @param budgetName the name of the budget to delete
     */
    public void deleteBudget(String budgetName) {

        Budget budget = getBudget(budgetName);
        if (budget == null) {
            budgetUi.printBudgetDoesNotExist();
            return;
        }

        budgets.remove(budget);
        budgetUi.printBudgetDelSuccessful(budget, budgets.size());
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
            budgetUi.printBudgetDoesNotExist();
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
            budgetUi.printBudgetDoesNotExist();
        } else {
            budgetUi.printListBudgets(foundBudgets);
        }
        foundBudgets.clear();
    }


    /**
     * Checks if a certain budget exists
     *
     * @param budgetName budget name to check for if it has been used
     */

    private static Budget getBudget(String budgetName) {
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
        budgetUi.printListBudgets(budgets);
    }


    /**
     * Prints user instructions on how to use budget commands
     */
    public void budgetHelp() {
        budgetUi.printBudgetCommands();
    }

    public static void detailedBudget(String budgetName) {
        Budget budget = getBudget(budgetName);
        if (budget == null) {
            budgetUi.printBudgetDoesNotExist();
        } else {
            ArrayList<Expense> expenses = ExpenseAction.expenses;
            double amountSpent = ExpenseUIResponse.printRelatedExpenses(expenses, budgetName);
            double ratio = amountSpent / budget.getLimit() * 20;
            Ui.printProgressBar(ratio);
            System.out.println("$ " + amountSpent + " out of $" + budget.getLimit() + " spent!");
        }
    }
}
