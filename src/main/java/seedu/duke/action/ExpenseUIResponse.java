package seedu.duke.action;

import java.time.LocalDate;
import java.util.ArrayList;

import seedu.duke.Ui;
import seedu.duke.model.Expense;
import seedu.duke.util.Constants;
import seedu.duke.util.Messages;

//@@author tzixi
public class ExpenseUIResponse {
    private Ui ui;

    public ExpenseUIResponse(Ui ui) {
        this.ui = ui;
    }

    public void printExpenseCommands() {
        String msg = String.format(Messages.EXPENSE_HELP_COMMANDS.toString());
        ui.printMessage(msg);
    }

    /**
     * Prints the added expense.
     *
     * @param expense expense that was just added
     */
    public void printExpenseAddSuccessful(Expense expense) {
        String msg = String.format(Messages.EXPENSE_EXPENSE.toString(), expense.getId(), expense.getCategory(),
            expense.getName(), expense.getAmount(), expense.getDate().format(Constants.OUTPUT_DATE_FORMAT));
        ui.printMessage(Messages.EXPENSE_ADD_SUCCESSFUL.toString(), msg);
    }

    /**
     * Prints the deleted expense.
     *
     * @param expense expense that was just deleted
     */
    public void printExpenseDelSuccessful(Expense expense) {
        String msg = String.format(Messages.EXPENSE_EXPENSE.toString(), expense.getId(), expense.getCategory(),
            expense.getName(), expense.getAmount(), expense.getDate().format(Constants.OUTPUT_DATE_FORMAT));
        ui.printMessage(Messages.EXPENSE_DELETE_SUCCESSFUL.toString(), msg);
    }

    /**
     * Prints the expenses for this current month along with
     * previous expenses.
     * 
     * @param previousExpenses list of previous expenses
     * @param currentExpenses list of current expenses
     */
    public void printListExpenses(ArrayList<Expense> previousExpenses, ArrayList<Expense> currentExpenses,
        String category) {

        if (previousExpenses.size() == 0 && currentExpenses.size() == 0) {
            ui.printMessage(Messages.EXPENSE_LIST_NOTHING.toString());
            return;
        }

        String categoryMsg = (category == null) ? Messages.EXPENSE_LIST_ALL.toString() : category;

        ArrayList<String> msgs = new ArrayList<String>();

        if (previousExpenses.size() != 0) {
            msgs.add(String.format(Messages.EXPENSE_LIST_PREVIOUS.toString(), categoryMsg));
            msgs.addAll(printExpenses(previousExpenses));
        }

        if (currentExpenses.size() != 0) {
            msgs.add(String.format(Messages.EXPENSE_LIST_CURRENT.toString(), categoryMsg));
            msgs.addAll(printExpenses(currentExpenses));
        }

        ui.printMessage(msgs.toArray(new String[msgs.size()]));
    }

    // @@author pinyoko573
    /**
     * Prints expenses that are filtered by the keyword.
     * 
     * @param expenses list of filtered expenses
     */
    public void printFindExpenses(ArrayList<Expense> expenses) {
        if (expenses.size() == 0) {
            ui.printMessage(Messages.EXPENSE_FIND_NOTHING.toString());
            return;
        }

        ArrayList<String> msgs = new ArrayList<String>();
        msgs.add(Messages.EXPENSE_FIND.toString());
        msgs.addAll(printExpenses(expenses));

        ui.printMessage(msgs.toArray(new String[msgs.size()]));
    }

    // @@author pinyoko573
    /**
     * Prints expenses that are filtered by date and/or category
     * 
     * @param expenses list of filtered deposits
     * @param from date displayed in output
     * @param to date displayed in output
     * @param category name of category displayed in output
     */
    public void printListExpensesRange(ArrayList<Expense> expenses, LocalDate from, LocalDate to, String category) {
        if (expenses.size() == 0) {
            ui.printMessage(Messages.EXPENSE_LIST_NOTHING.toString());
            return;
        }

        String categoryMsg = (category == null) ? Messages.EXPENSE_LIST_ALL.toString() : category;

        ArrayList<String> msgs = new ArrayList<String>();

        String msg;
        if (from.equals(LocalDate.MIN)) {
            msg = String.format(Messages.EXPENSE_LIST_RANGE_TO.toString(), to.format(Constants.OUTPUT_DATE_FORMAT),
                categoryMsg);
        } else if (to.equals(LocalDate.MAX)) {
            msg = String.format(Messages.EXPENSE_LIST_RANGE_FROM.toString(), from.format(Constants.OUTPUT_DATE_FORMAT),
                categoryMsg);
        } else {
            msg = String.format(Messages.EXPENSE_LIST_RANGE.toString(), from.format(Constants.OUTPUT_DATE_FORMAT),
                to.format(Constants.OUTPUT_DATE_FORMAT), categoryMsg);
        }
        msgs.add(msg);

        msgs.addAll(printExpenses(expenses));
        ui.printMessage(msgs.toArray(new String[msgs.size()]));
    }

    // @@author pinyoko573
    /**
     * Prints the expenses that are cleared.
     * 
     * @param expenses list of expenses
     */
    public void printClearExpenses(ArrayList<Expense> expenses) {
        ArrayList<String> msgs = new ArrayList<String>();

        msgs.add(Messages.EXPENSE_CLEAR_SUCCESSFUL.toString());
        
        msgs.addAll(printExpenses(expenses));
        ui.printMessage(msgs.toArray(new String[msgs.size()]));
    }

    /**
     * Prints the expenses in the given format in messages
     * 
     * @param expenses list of expenses
     */
    public static ArrayList<String> printExpenses(ArrayList<Expense> expenses) {
        ArrayList<String> msgs = new ArrayList<String>();
        for (Expense e : expenses) {
            String msg = String.format(Messages.EXPENSE_EXPENSE.toString(), e.getId(), e.getCategory(),
                e.getName(), e.getAmount(), e.getDate().format(Constants.OUTPUT_DATE_FORMAT));
            msgs.add(msg);
        }

        return msgs;
    }


}
