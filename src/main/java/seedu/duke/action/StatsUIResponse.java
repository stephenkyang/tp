package seedu.duke.action;

import java.util.ArrayList;

import seedu.duke.Ui;
import seedu.duke.util.Commons;
import seedu.duke.util.Messages;

//@@author SaiChaitanya13
public class StatsUIResponse {
    private Ui ui;

    public StatsUIResponse(Ui ui) {
        this.ui = ui;
    }

    public void printStatsCommands() {
        String msg = String.format(Messages.STATS_HELP_COMMANDS.toString());
        ui.printMessage(msg);
    }

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

    private ArrayList<String> printBudgetStats(int month, int year, ArrayList<String> budgetMsg) {
        // Convert month to string
        String monthString = Commons.convertMonthToString(month);

        ArrayList<String> msgs = new ArrayList<String>();
        msgs.add(String.format(Messages.STATS_PRINT_INTRO.toString(), monthString, year));

        msgs.addAll(budgetMsg);

        return msgs;
    }

    private ArrayList<String> printDepositStats(double totalDeposits, ArrayList<String> depositMsg) {
        ArrayList<String> msgs = new ArrayList<String>();
        msgs.add(Messages.STATS_PRINT_DEPOSITS.toString());

        if (depositMsg != null) {
            msgs.addAll(depositMsg);
        }

        msgs.add(String.format(Messages.STATS_PRINT_DEPOSITS_TOTAL.toString(), totalDeposits));
        return msgs;
    }

    private ArrayList<String> printExpenseStats(double totalExpenses, ArrayList<String> expenseMsg) {
        ArrayList<String> msgs = new ArrayList<String>();
        msgs.add(Messages.STATS_PRINT_EXPENSES.toString());

        if (expenseMsg != null) {
            msgs.addAll(expenseMsg);
        }

        msgs.add(String.format(Messages.STATS_PRINT_EXPENSES_TOTAL.toString(), totalExpenses));
        return msgs;
    }

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
