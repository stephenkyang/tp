package seedu.duke.action;

import seedu.duke.model.Budget;

import java.util.ArrayList;

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
     * @param budgetName the name of the budget that the user wants to create
     * @param budgetLimit the monetary limit of the budget
     */
    public void addBudget(String budgetName, double budgetLimit) {
        // Check if there are any duplicate budgets
        if (checkDuplicateBudget(budgetName)) {
            System.out.println("A budget with the same name exists!");
            return;
        }

        Budget budget = new Budget(budgetName, budgetLimit);
        budgets.add(budget);
    }

    public void deleteBudget(String budgetName) {
        // int i = 0;
        // boolean budgetExists = false;
        // int budgetIndex = -1;
        // for (Budget a : budgets) {
        //     if (Objects.equals(a.budgetName, budgetName)) {
        //         budgetExists = true;
        //         budgetIndex = budgets.indexOf(a);
        //     }
        // }

        // if (budgetExists) {
        //     budgets.remove(budgetIndex);
        // } else {
        //     System.out.println("This budget does not exist!");
        // }

        Budget budget = getBudget(budgetName);
        if (budget == null) {
            System.out.println("This budget does not exist!");
            return;
        }

        budgets.remove(budget);
    }

    public void setBudget(String budgetName, double budgetLimit) {
        Budget budget = getBudget(budgetName);
        if (budget == null) {
            System.out.println("This budget does not exist!");
            return;
        }

        budget.setLimit(budgetLimit);
    }

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
     *
     * @return
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
        System.out.println("Total of " + budgets.size() + " budgets.");
    }
}
