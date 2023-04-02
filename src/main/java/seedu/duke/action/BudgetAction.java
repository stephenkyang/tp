package seedu.duke.action;

import seedu.duke.Ui;
import seedu.duke.exception.GlobalInvalidMonthYearException;
import seedu.duke.model.Budget;
import seedu.duke.model.Expense;
import seedu.duke.util.Commons;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

//@@author chongyongrui

/**
 * Contains methods related to budget function.
 */
public class BudgetAction {
    private static BudgetUIResponse budgetUi;
    private ArrayList<Budget> budgets;

    public BudgetAction(ArrayList<Budget> budgets, Ui ui) {
        this.budgets = budgets;
        budgetUi = new BudgetUIResponse(ui);
    }

    public ArrayList<Budget> getBudgets() {
        return this.budgets;
    }

    /**
     * Creates a budget of name and limit determined by user input.
     *
     * @param budgetName  the name of the budget that the user wants to create
     * @param budgetLimit the monetary limit of the budget
     */
    public void addBudget(String budgetName, double budgetLimit) {
        // Check if there are any duplicate budgets
        if (validateBudget(budgetName, budgets)) {
            budgetUi.printBudgetNameUsed();
            return;
        } else if (budgetLimit < 0) {
            // budgetUi.printBudgetLimitNegative();
            return;
        }

        Budget budget = new Budget(budgetName, budgetLimit);
        budgets.add(budget);
        assert budget.getAmount() >= 0 : "Budget limit is negative!";

        budgetUi.printBudgetAddSuccessful(budget, budgets.size());
    }

    /**
     * Deletes a budget from the budget list.
     *
     * @param budgetName the name of the budget to delete
     * @param expenses   list of expense that will be used to delete expense containing budgetName
     */
    public void deleteBudget(String budgetName, ArrayList<Expense> expenses) {
        Budget budget = getBudget(budgetName);
        if (budget == null) {
            budgetUi.printBudgetDoesNotExist();
            return;
        }

        assert budgets.indexOf(budget) < budgets.size() : "budget should not exist!";


        // Clears the expenses if budget delete too
        ExpenseAction.clearExpensesByCategory(budgetName, expenses);

        budgets.remove(budget);
        budgetUi.printBudgetDelSuccessful(budget, budgets.size());
    }

    /**
     * Modifies the budget limit of a chose budget.
     *
     * @param budgetName  the budget to modify the budget limit for
     * @param budgetLimit the new budget limit
     */
    public void setBudget(String budgetName, double budgetLimit) {
        Budget budget = getBudget(budgetName);
        if (budget == null) {
            budgetUi.printBudgetDoesNotExist();
            return;
        } else if (budgetLimit < 0) {
            // budgetUi.printBudgetLimitNegative();
            return;
        }
        assert budgets.contains(budget) : "budget does not exist!";
        budget.setAmount(budgetLimit);
        budgetUi.printBudgetSetSuccessful(budget, budgets.size());
    }


    /**
     * Checks if a certain budget exists.
     *
     * @param budgetName budget name to check for if it has been used
     * @return budget object which is found. Else, null is returned.
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
     * Prints all the details of all budgets in the list.
     *
     * @param month Month of the budget that user wants to view
     * @param year Year of the budget that user wants to view
     * @param expenses Calculate expense total of each budget category 
     * @throws GlobalInvalidMonthYearException invalid date that cannot be parsed
     */
    public void printBudgets(int month, int year, ArrayList<Expense> expenses) throws GlobalInvalidMonthYearException {
        // Check if month and year is valid
        LocalDate endDate = Commons.isValidMonthYear(month, year);
        LocalDate startDate = endDate.with(TemporalAdjusters.firstDayOfMonth());

        // Used to format the printing
        int longestBudgetName = getLongestBudgetName(budgets);

        // Get the total expenses of each budget
        double[] budgetsExpenseTotal = getBudgetsExpenseTotal(budgets, expenses, startDate, endDate);

        budgetUi.printListBudgets(budgets, budgetsExpenseTotal, month, year, longestBudgetName);
    }

    /**
     * Gets the longest budget name for Ui space formatting.
     *
     * @param budgets containing the list of budgets
     * @return size of the longest budget name
     */
    public static int getLongestBudgetName(ArrayList<Budget> budgets) {
        int longestBudgetName = 0;

        for (Budget b : budgets) {
            String category = b.getName();

            if (category.length() > longestBudgetName) {
                longestBudgetName = category.length();
            }
        }

        return longestBudgetName;
    }

    /**
     * Gets the total expense of each budget.
     *
     * @param budgets   containing the list of budgets
     * @param expenses  use to calculate total expense based on category
     * @param startDate start date range for expense
     * @param endDate   end date range for expense
     * @return array of double that contains total expense of each budget
     */
    public static double[] getBudgetsExpenseTotal(ArrayList<Budget> budgets, ArrayList<Expense> expenses,
        LocalDate startDate, LocalDate endDate) {

        double[] budgetsExpenseTotal = new double[budgets.size()];

        int i = 0;
        for (Budget b : budgets) {
            String category = b.getName();

            ArrayList<Expense> filteredExpenses = ExpenseAction.filterExpensesByCategory(expenses, category);
            filteredExpenses = ExpenseAction.filterExpensesByDate(filteredExpenses, startDate, endDate);

            budgetsExpenseTotal[i] = ExpenseAction.getTotalExpenses(filteredExpenses);
            i++;
        }

        return budgetsExpenseTotal;
    }

    /**
     * Prints user instructions on how to use budget commands
     */
    public void budgetHelp() {
        budgetUi.printBudgetCommands();
    }

    /**
     * Checks if a certain budget name already exists
     *
     * @param budgetName budget name to check for if it has been used
     * @param budgets    containing the list of budgets
     * @return true if budget name exists in the budget list, else false
     */
    protected static boolean validateBudget(String budgetName, ArrayList<Budget> budgets) {
        for (Budget b : budgets) {
            if (b.getName().equals(budgetName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the total amount of all budgets.
     *
     * @param  budgets containing the list of budgets
     * @return total amount of all budgets
     */
    public static double getTotalBudgets(ArrayList<Budget> budgets) {
        double total = 0;
        for (Budget b : budgets) {
            total += b.getAmount();
        }

        return total;
    }

    /**
     * Prints a message about budgets that are close to the limit upon the initialisation of Duke.
     * 
     * @param budgets   containing the list of budgets
     * @param expenses  use to calculate total expense based on category
     * @return list of messages that will be printed in Ui from Main
     */
    public static ArrayList<String> summaryBudget(ArrayList<Budget> budgets, ArrayList<Expense> expenses) {
        ArrayList<String> msgs = new ArrayList<String>();

        // check if there is budget data
        if (budgets.size() == 0) {
            return msgs;
        }

        // for each budget, get expenses total on current month
        LocalDate startDate = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());

        double[] budgetsExpenseTotal = getBudgetsExpenseTotal(budgets, expenses, startDate, endDate);
        int longestBudgetName = getLongestBudgetName(budgets);

        msgs.addAll(BudgetUIResponse.getSummaryBudget(budgets, budgetsExpenseTotal, longestBudgetName));
        return msgs;
    }

    // /**
    //  * Finds if a budget contains the keywords input by user
    //  *
    //  * @param keyword the word the user wants to check for
    //  */
    // public void findBudget(String keyword) {
    //     ArrayList<Budget> foundBudgets = new ArrayList<>();
    //     for (Budget budget : budgets) {

    //         if (budget.getName().contains(keyword)) {
    //             foundBudgets.add(budget);
    //         }
    //     }

    //     if (foundBudgets.isEmpty()) {
    //         budgetUi.printBudgetDoesNotExist();
    //     } else {
    //         budgetUi.printFindBudgets(foundBudgets);
    //     }
    //     foundBudgets.clear();
    // }

    // /**
    //  * Prints the budget progress bar
    //  *
    //  * @param ratio the percentage of what is spent compared to the budget limit
    //  */
    // public static void printBudgetDetailBar(double ratio) {
    //     int numberOfBlocks = 0;
    //     if ((int) ratio >= 1) {
    //         numberOfBlocks = 40;
    //     } else {
    //         numberOfBlocks = (int) (ratio * 40);
    //     }
    //     int excess = (int) ratio;
    //     int i = 0;
    //     int numberOfBlanks = 40 - numberOfBlocks;
    //     while (i < numberOfBlocks) {
    //         if (excess == 0) {
    //             System.out.print("█");
    //         } else {
    //             System.out.print(Constants.ANSI_RED + "█" + Constants.ANSI_RESET);
    //         }
    //         i++;
    //     }
    //     i = 0;
    //     while (i < numberOfBlanks) {
    //         System.out.print("░");
    //         i++;
    //     }
    //     System.out.println(" ");
    //     if (ratio >= 1) {
    //         System.out.println("You have exceeded the budget limit by " + (ratio - 1) * 100 + "%!");
    //     } else {
    //         System.out.println(ratio * 100 + "% of your budget has been spent!");
    //     }
    // }
}
