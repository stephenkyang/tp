package seedu.duke;

import java.util.ArrayList;
import java.util.Objects;

public class BudgetList {
    public static ArrayList<Budget> budgets = new ArrayList<Budget>();


    /**
     * Checks if a certain budget name has already been used
     *
     * @param budgetName budget name to check for if it has been used
     */

    public static boolean duplicateBudgetName(String budgetName) {
        int budgetIndex = findBudget(budgetName);
        if (budgetIndex != -1){
            return true;
        }
        return false;

    }

    /**
     * Find the index of a budget with name specified by user input
     * @param budgetName the budget that the user wants to search for
     * @return the index of the budget in the budgets ArrayList
     */

    public static int findBudget(String budgetName) {

        int budgetIndex = -1;
        for (Budget a : budgets) {
            if (Objects.equals(a.budgetName, budgetName)) {
                System.out.println(a.budgetName + "is found");
                budgetIndex = budgets.indexOf(a);
            }
        }

        return budgetIndex;
    }

    /**
     * Creates a budget of name and limit determined by user input
     *
     * @param budgetName the name of the budget that the user wants to create
     * @param budgetLimit the monetary limit of the budget
     */

    public static void createBudget(String budgetName, double budgetLimit) {

        Budget budget = new Budget(budgetName, budgetLimit);
        budgets.add(budget);
    }


    /**
     * Deletes a budget from the budgets ArrayList
     * @param budgetName the budget to delete
     */

    public static void deleteBudget(String budgetName) {
        System.out.println(budgetName + "to delete");
        int budgetIndex = findBudget(budgetName);

        if (budgetIndex != -1 ) {
            budgets.remove(budgetIndex);
        } else {
            System.out.println("This budget does not exist!");
        }
    }


    /**
     * Prints all the details of all budgets in the list
     */
    public static void printTaskList() {
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
