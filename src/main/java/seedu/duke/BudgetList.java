package seedu.duke;

import java.util.ArrayList;
import java.util.Objects;

public class BudgetList {
    public static ArrayList<Budget> budgets = new ArrayList<Budget>();


    /**
     * Creates a budget of name and limit determined by user input
     *
     * @param budgetName the name of the budget that the user wants to create
     * @param budgetLimit the monetary limit of the budget
     */

    /**
     * Checks if a certain budget name has already been used
     *
     * @param budgetName budget name to check for if it has been used
     */

    public static boolean duplicateBudgetName(String budgetName) {
        boolean budgetExists = false;
        for (Budget a : budgets) {
            if (Objects.equals(a.budgetName, budgetName)) {
                budgetExists = true;
                break;
            }
        }
        return budgetExists;

    }

    public static void createBudget(String budgetName, double budgetLimit) {

        Budget budget = new Budget(budgetName, budgetLimit);
        budgets.add(budget);
    }

    public static void deleteBudget(String budgetName) {
        int i = 0;
        boolean budgetExists = false;
        int budgetIndex = -1;
        for (Budget a : budgets) {
            if (Objects.equals(a.budgetName, budgetName)) {
                budgetExists = true;
                budgetIndex = budgets.indexOf(a);
            }
        }

        if (budgetExists) {
            budgets.remove(budgetIndex);
        } else {
            System.out.println("This budget does not exist!");
        }
    }


    /**
     * Prints all the details of all budgets in the list
     *
     * @return
     */
    public static short printTaskList() {
        int i = 1;
        for (Budget a : budgets) {
            if (a != null) {
                System.out.print(i + ". ");
                a.printBudget();
                i++;
            }
        }
        System.out.println("Total of " + budgets.size() + " budgets.");
        return 0;
    }


}
