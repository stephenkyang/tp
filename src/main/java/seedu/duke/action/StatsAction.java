package seedu.duke.action;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.exception.BBException;
import seedu.duke.model.Budget;
import seedu.duke.model.Deposit;
import seedu.duke.model.Expense;
import seedu.duke.util.Commons;

//@@author SaiChaitanya13
public class StatsAction {
    private StatsUIResponse statsUi;

    private ArrayList<Budget> budgets;
    private ArrayList<Deposit> deposits;
    private ArrayList<Expense> expenses;

    /**
     * Constructor for StatsAction class
     *
     * @param data Data of the various transactions
     * @param ui UI of the application
     */
    public StatsAction(Data data, Ui ui) {
        this.budgets = data.getBudgets();
        this.deposits = data.getDeposits();
        this.expenses = data.getExpenses();

        statsUi = new StatsUIResponse(ui);
    }

    /**
     * Function shows the different stats and prints them, filtered based on date and checks if the date is valid
     *
     * @param month Current month
     * @param year Current year
     * @param showDeposit If there are deposits
     * @param showExpense If there are expenses
     * @throws BBException Throws exception for unknown and invalid inputs
     */
    public void showStats(int month, int year, boolean showDeposit, boolean showExpense) throws BBException {
        // Check if month and year is valid
        LocalDate endDate = Commons.isValidMonthYear(month, year);
        LocalDate startDate = endDate.with(TemporalAdjusters.firstDayOfMonth());

        // Filter deposits by date
        ArrayList<Deposit> filteredDeposits = DepositAction.filterDepositsByDate(deposits, startDate, endDate);
        double totalDeposits = DepositAction.getTotalDeposits(filteredDeposits);

        // Filter expenses by date
        ArrayList<Expense> filteredExpenses = ExpenseAction.filterExpensesByDate(expenses, startDate, endDate);
        double totalExpenses = ExpenseAction.getTotalExpenses(filteredExpenses);

        ArrayList<String> budgetMsg = showBudget(startDate, endDate, month, year, filteredExpenses);
        double totalBudgets = BudgetAction.getTotalBudgets(budgets);

        ArrayList<String> depositMsg = null;
        ArrayList<String> expenseMsg = null;

        if (showDeposit) {
            depositMsg = DepositUIResponse.printDeposits(filteredDeposits);
        }

        if (showExpense) {
            expenseMsg = ExpenseUIResponse.printExpenses(filteredExpenses);
        }

        statsUi.printStats(month, year, totalBudgets, totalDeposits, totalExpenses, budgetMsg, depositMsg, expenseMsg);
    }

    /**
     * Prints user instructions on how to use budget commands
     */
    public void statsHelp() {
        statsUi.printStatsCommands();
    }

    /**
     * Gets the budgets that are present
     *
     * @param startDate Start date of budgets to be shown
     * @param endDate End date of budgets to be shown
     * @param month Current month
     * @param year Current year
     * @param filteredExpenses Filtered expenses
     * @return Messages to be printed out to the user
     */
    private ArrayList<String> showBudget(LocalDate startDate, LocalDate endDate,
        int month, int year, ArrayList<Expense> filteredExpenses) {

        // Used to format the printing
        int longestBudgetName = BudgetAction.getLongestBudgetName(budgets);

        // Get the total expenses of each budget
        double[] budgetsExpenseTotal = BudgetAction.getBudgetsExpenseTotal(budgets, filteredExpenses,
            startDate, endDate);

        ArrayList<String> msgs = BudgetUIResponse
            .getListBudgetsMsg(budgets, budgetsExpenseTotal, longestBudgetName);

        return msgs;
    }
}
