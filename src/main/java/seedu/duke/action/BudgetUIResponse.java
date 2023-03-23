package seedu.duke.action;

import seedu.duke.Ui;
import seedu.duke.model.Budget;
import seedu.duke.util.Commons;
import seedu.duke.util.CommonsUi;
import seedu.duke.util.Messages;

import java.util.ArrayList;

//@@author chongyongrui

/**
 * Contains User Interface text responses when a budget method is run
 */
public class BudgetUIResponse {
    private Ui ui;

    public BudgetUIResponse(Ui ui) {
        this.ui = ui;
    }

    public void printBudgetDoesNotExist() {
        ui.printMessage(Messages.BUDGET_DOES_NOT_EXIST.toString());
    }

    public void printBudgetNameUsed() {
        ui.printMessage(Messages.BUDGET_NAME_USED.toString());
    }

    public void printBudgetLimitNegative() {
        ui.printMessage(Messages.BUDGET_LIMIT_NEGATIVE.toString());
    }

    public void printBudgetAddSuccessful(Budget budget, int count) {
        String msg = String.format(Messages.BUDGET_ADD_SUCCESSFUL.toString(), budget.getName(), budget.getAmount());
        String countMsg = String.format(Messages.BUDGET_NUMBER_OF.toString(), count);
        ui.printMessage(msg, countMsg);
    }

    public void printBudgetDelSuccessful(Budget budget, int count) {
        String msg = String.format(Messages.BUDGET_DELETE_SUCCESSFUL.toString(), budget.getName());
        String countMsg = String.format(Messages.BUDGET_NUMBER_OF.toString(), count);
        ui.printMessage(msg, countMsg);
    }

    public void printBudgetSetSuccessful(Budget budget, int count) {
        String msg = String.format(Messages.BUDGET_SET_SUCCESSFUL.toString(), budget.getName(), budget.getAmount());
        String countMsg = String.format(Messages.BUDGET_NUMBER_OF.toString(), count);
        ui.printMessage(msg, countMsg);
    }

    public void printBudgetCommands() {
        String msg = String.format(Messages.BUDGET_HELP_COMMANDS.toString());
        ui.printMessage(msg);
    }

    public static ArrayList<String> getListBudgetsMsg(ArrayList<Budget> budgets, double[] budgetExpensesTotal,
                                                      int longestBudgetName) {

        ArrayList<String> msgs = new ArrayList<String>();

        msgs.addAll(printBudgets(budgets, budgetExpensesTotal, longestBudgetName));

        return msgs;
    }

    public void printListBudgets(ArrayList<Budget> budgets, double[] budgetExpensesTotal, int month,
                                 int year, int longestBudgetName) {

        if (budgets.size() == 0) {
            ui.printMessage(Messages.BUDGET_LIST_NOTHING.toString());
            return;
        }

        ArrayList<String> msgs = new ArrayList<String>();

        // Convert month to string
        String monthString = Commons.convertMonthToString(month);

        String msg = String.format(Messages.BUDGET_LIST.toString(), monthString, year);
        msgs.add(msg);

        msgs.addAll(getListBudgetsMsg(budgets, budgetExpensesTotal, longestBudgetName));

        ui.printMessage(msgs.toArray(new String[msgs.size()]));
    }

    public static ArrayList<String> printBudgets(ArrayList<Budget> budgets, double[] budgetExpensesTotal,
                                                 int longestBudgetName) {

        ArrayList<String> msgs = new ArrayList<String>();

        int i = 1;
        for (Budget b : budgets) {
            String barNameFormat = "%-" + longestBudgetName + "s " +
                    CommonsUi.formatBar(budgetExpensesTotal[i - 1], b.getAmount());
            String barName = String.format(barNameFormat, b.getName());

            String msg = String.format(Messages.BUDGET_BUDGET.toString(), i, barName,
                    budgetExpensesTotal[i - 1], b.getAmount());

            msgs.add(msg);
            i++;
        }

        return msgs;
    }

    // public void printFindBudgets(ArrayList<Budget> budgets) {
    //     ArrayList<String> msgs = new ArrayList<String>();
    //     msgs.AddAll()
    // }
}
