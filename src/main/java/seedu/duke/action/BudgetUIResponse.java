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

    /**
     * Prints the added budget along with the total no of budgets.
     * 
     * @param budget budget that was just added
     * @param count total no of budgets
     */
    public void printBudgetAddSuccessful(Budget budget, int count) {
        String msg = String.format(Messages.BUDGET_ADD_SUCCESSFUL.toString(), budget.getName(), budget.getAmount());
        String countMsg = String.format(Messages.BUDGET_NUMBER_OF.toString(), count);
        ui.printMessage(msg, countMsg);
    }

    /**
     * Prints the deleted budget along with the total no of budgets.
     * 
     * @param budget budget that was just deleted
     * @param count total no of budgets
     */
    public void printBudgetDelSuccessful(Budget budget, int count) {
        String msg = String.format(Messages.BUDGET_DELETE_SUCCESSFUL.toString(), budget.getName());
        String countMsg = String.format(Messages.BUDGET_NUMBER_OF.toString(), count);
        ui.printMessage(msg, countMsg);
    }

    /**
     * Prints the deleted budget along with the total no of budgets.
     * 
     * @param budget budget that was just deleted
     * @param count total no of budgets
     */
    public void printBudgetSetSuccessful(Budget budget, int count) {
        String msg = String.format(Messages.BUDGET_SET_SUCCESSFUL.toString(), budget.getName(), budget.getAmount());
        String countMsg = String.format(Messages.BUDGET_NUMBER_OF.toString(), count);
        ui.printMessage(msg, countMsg);
    }

    /**
     * Prints the list of budget commands.
     */
    public void printBudgetCommands() {
        String msg = String.format(Messages.BUDGET_HELP_COMMANDS.toString());
        ui.printMessage(msg);
    }

    /**
     * Gets the list of strings containing each budget information,
     * such as budget name, limit and total expense.
     * Used for displaying summary.
     * 
     * @param budgets list of budgets
     * @param budgetExpensesTotal total amount of expense for each budget
     * @param longestBudgetName indention for aligning budget name
     * @return list of strings that will be used for displaying summary
     */
    public static ArrayList<String> getListBudgetsMsg(ArrayList<Budget> budgets, double[] budgetExpensesTotal,
        int longestBudgetName) {

        ArrayList<String> msgs = new ArrayList<String>();

        msgs.addAll(printBudgets(budgets, budgetExpensesTotal, longestBudgetName));

        return msgs;
    }

    /**
     * Prints the list of budgets.
     * Used for budget list.
     * 
     * @param budgets budgets list of budgets
     * @param budgetExpensesTotal total amount of expense for each budget
     * @param month month of the budget information
     * @param year year of the budget information
     * @param longestBudgetName indention for aligning budget name
     */
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

    /**
     * Returns the list of strings containing each budget information,
     * such as budget name, limit and total expense.
     * Used for constructing the string for each budget.
     * 
     * @param budgets budgets list of budgets
     * @param budgetExpensesTotal total amount of expense for each budget
     * @param longestBudgetName indention for aligning budget name
     * @return list of strings containing each budget information
     */
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

    /**
     * Prints the summary message and budget 
     * 
     * @param budgets budgets list of budgets
     * @param budgetExpensesTotal total amount of expense for each budget
     * @param longestBudgetName indention for aligning budget name
     * @return list of strings of summary budget, used for displaying in Stats Ui
     */
    public static ArrayList<String> getSummaryBudget(ArrayList<Budget> budgets,
        double[] budgetExpensesTotal, int longestBudgetName) {

        ArrayList<String> msgs = new ArrayList<String>();
        msgs.add(Messages.BUDGET_CURRENT_PROGRESS.toString());
        
        msgs.addAll(printBudgets(budgets, budgetExpensesTotal, longestBudgetName));

        return msgs;
    }
}
