package seedu.duke.action;

import java.util.ArrayList;

import seedu.duke.Ui;
import seedu.duke.util.Commons;
import seedu.duke.util.Messages;

//@@author SaiChaitanya13
public class StatsUIResponse {
    private Ui ui;

    /**
     * Constructor for StatsUIResponse class
     *
     * @param ui UI of the application
     */
    public StatsUIResponse(Ui ui) {
        this.ui = ui;
    }

    /**
     * Prints Stats Help command
     */
    public void printStatsCommands() {
        String msg = String.format(Messages.STATS_HELP_COMMANDS.toString());
        ui.printMessage(msg);
    }

    /**
     * Prints all the stats
     *
     * @param month Current month
     * @param year Current year
     * @param totalBudgets Total budgets
     * @param totalDeposits Total deposits
     * @param totalExpenses Total expenses
     * @param budgetMsg Message for the budgets
     * @param depositMsg Message for the deposits
     * @param expenseMsg Message for the expenses
     */
    public void printStats(int month, int year, double totalBudgets, double totalDeposits, double totalExpenses,
        ArrayList<String> budgetMsg, ArrayList<String> depositMsg, ArrayList<String> expenseMsg) {

        if (budgetMsg.size() == 0) {
            ui.printMessage(Messages.STATS_PRINT_NO_STATS.toString());
            return;
        }

        ArrayList<String> msgs = new ArrayList<String>();

        msgs.addAll(printBudgetStats(month, year, budgetMsg));
        msgs.addAll(printDepositStats(totalDeposits, depositMsg));
        msgs.addAll(printExpenseStats(totalExpenses, expenseMsg));
        msgs.addAll(printSummary(totalBudgets, totalDeposits, totalExpenses));

        ui.printMessage(msgs.toArray(new String[msgs.size()]));
    }

    /**
     * Prints stats for Budget
     *
     * @param month Current month
     * @param year Current year
     * @param budgetMsg Arraylist with budget messages
     * @return Arraylist with all stats and ui for Budget
     */
    private ArrayList<String> printBudgetStats(int month, int year, ArrayList<String> budgetMsg) {
        // Convert month to string
        String monthString = Commons.convertMonthToString(month);

        ArrayList<String> msgs = new ArrayList<String>();
        msgs.add(String.format(Messages.STATS_PRINT_INTRO.toString(), monthString, year));

        msgs.addAll(budgetMsg);

        return msgs;
    }

    /**
     * Prints stats for Deposit
     *
     * @param totalDeposits Total amount of deposits
     * @param depositMsg Arraylist containing the messages for Deposit
     * @return Arraylist containing all stats and ui for Deposit
     */
    private ArrayList<String> printDepositStats(double totalDeposits, ArrayList<String> depositMsg) {
        ArrayList<String> msgs = new ArrayList<String>();
        msgs.add(Messages.STATS_PRINT_DEPOSITS.toString());

        if (depositMsg != null) {
            msgs.addAll(depositMsg);
        }

        msgs.add(String.format(Messages.STATS_PRINT_DEPOSITS_TOTAL.toString(), totalDeposits));
        return msgs;
    }

    /**
     * Print stats for Expense
     *
     * @param totalExpenses Total amount of expenses
     * @param expenseMsg Arraylist containing the messages for Expense
     * @return Arraylist containing all stats and ui for Expense
     */
    private ArrayList<String> printExpenseStats(double totalExpenses, ArrayList<String> expenseMsg) {
        ArrayList<String> msgs = new ArrayList<String>();
        msgs.add(Messages.STATS_PRINT_EXPENSES.toString());

        if (expenseMsg != null) {
            msgs.addAll(expenseMsg);
        }

        msgs.add(String.format(Messages.STATS_PRINT_EXPENSES_TOTAL.toString(), totalExpenses));
        return msgs;
    }

    /**
     * Prints the summary of the stats (if on right track or overspending)
     *
     * @param totalBudgets Total amount of budgets
     * @param totalDeposits Total amount of deposits
     * @param totalExpenses Total amount of expenses
     * @return Messages which show ui of total amount of budgets, expenses and deposits and wheter on right track or overspent
     */
    private ArrayList<String> printSummary(double totalBudgets, double totalDeposits, double totalExpenses) {
        ArrayList<String> msgs = new ArrayList<String>();
        String comment = Messages.STATS_PRINT_RIGHT_TRACK.toString();

        if (totalExpenses > (totalBudgets + totalDeposits)) {
            comment = Messages.STATS_PRINT_OVERSPEND.toString();
        }

        msgs.add(String.format(Messages.STATS_PRINT_BUDGET_PROGRESS.toString(),
            totalExpenses, totalBudgets, totalDeposits));
        msgs.add(comment);

        return msgs;
    }
}
