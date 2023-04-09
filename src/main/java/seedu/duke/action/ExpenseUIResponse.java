package seedu.duke.action;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

import seedu.duke.Ui;
import seedu.duke.exception.GlobalInvalidMonthYearException;
import seedu.duke.model.Expense;
import seedu.duke.model.Budget;
import seedu.duke.util.Commons;
import seedu.duke.util.CommonsUi;
import seedu.duke.util.Constants;
import seedu.duke.util.Messages;

/**
 * Contains User Interface text responses when an expense method is run
 */

//@@author tzixi

public class ExpenseUIResponse {
    private Ui ui;

    public ExpenseUIResponse(Ui ui) {
        this.ui = ui;
    }

    /**
     * Function prints expense help commands
     */
    public void printExpenseCommands() {
        String msg = String.format(Messages.EXPENSE_HELP_COMMANDS.toString());
        ui.printMessage(msg);
    }

    /**
     * Function prints message when expense is added successfully
     * @param expense expense object that is being added
     */
    public void printExpenseAddSuccessful(Expense expense) {
        String msg = String.format(Messages.EXPENSE_EXPENSE.toString(), expense.getId(), expense.getCategory(),
            expense.getName(), expense.getAmount(), expense.getDate().format(Constants.OUTPUT_DATE_FORMAT));
        ui.printMessage(Messages.EXPENSE_ADD_SUCCESSFUL.toString(), msg);
    }


    /**
     * Function returns Budget based on the name of the category
     *
     * @param category category name of budget
     * @param budgets arraylist containing all budgets
     * @return Object type Budget with that category name
     */

    //@@author SaiChaitanya13
    public Budget getBudget(String category, ArrayList<Budget> budgets){
        for (Budget b : budgets){
            if (b.getName().equals(category)){
                return b;
            }
        }

        return null;
    }

    /**
     * Function prints Budget Status with the progress bar of expense / budget for the category of the expense added
     *
     * @param expense Expense added
     * @param startDate Start date
     * @param endDate End date
     * @param budgets Arraylist containing all budgets
     * @param expenses Arraylist containing all expenses
     * @throws GlobalInvalidMonthYearException
     */

    //@@author SaiChaitanya13
    public void printBudgetStatus(Expense expense, LocalDate startDate, LocalDate endDate, ArrayList<Budget> budgets, ArrayList<Expense> expenses) throws GlobalInvalidMonthYearException {
        // get category of expense added
        String category = expense.getCategory();
        //LocalDate endDate = Commons.isValidMonthYear(month, year);
        //LocalDate startDate = endDate.with(TemporalAdjusters.firstDayOfMonth());

        // filter expenses based on category
        ArrayList<Expense> filteredExpenses = ExpenseAction.filterExpensesByCategory(expenses, category);
        // filter expenses based on date (current month to date)
        filteredExpenses = ExpenseAction.filterExpensesByDate(filteredExpenses, startDate, endDate);

        // get individual category total expense for the current month to date
        double categoryExpenseTotal = ExpenseAction.getTotalExpenses(filteredExpenses);

        // get the budget for this category
        Budget budgetForCategory;
        budgetForCategory = getBudget(category, budgets);

        // get amount of budget for this category
        double categoryBudget = budgetForCategory.getAmount();

        String barFormat = "%-" + category + "s " + CommonsUi.formatBar(categoryExpenseTotal, categoryBudget);
        String barString = String.format(barFormat, category);

        String currentMonth = String.valueOf(startDate.getMonth());
        int currentYear = startDate.getYear();
        String msg = String.format(Messages.EXPENSE_ADD_BUDGET_STATUS.toString(), category, currentMonth, currentYear, barString, categoryExpenseTotal, categoryBudget);
        ui.printMessage(msg);


    }

    /**
     * Function prints message when expense is deleted successfully
     * @param expense Expense being deleted
     */

    public void printExpenseDelSuccessful(Expense expense) {
        String msg = String.format(Messages.EXPENSE_EXPENSE.toString(), expense.getId(), expense.getCategory(),
            expense.getName(), expense.getAmount(), expense.getDate().format(Constants.OUTPUT_DATE_FORMAT));
        ui.printMessage(Messages.EXPENSE_DELETE_SUCCESSFUL.toString(), msg);
    }

    /**
     * Function prints the list of expenses
     *
     * @param previousExpenses Arraylist containing previous expenses
     * @param currentExpenses Arraylist containing current expenses
     * @param category category of expenses
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

    /**
     * Function prints the expenses found
     *
     * @param expenses Arraylist containing all expenses
     */
    // @@author pinyoko573
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

    /**
     * Function prints expenses based on date range
     *
     * @param expenses Arraylist containing all expenses
     * @param from Expenses start date
     * @param to Expenses end date
     * @param category Category of expenses
     */
    // @@author pinyoko573
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

    /**
     * Prints message when clearing expenses
     *
     * @param expenses Arraylist containing all expenses
     */
    // @@author pinyoko573
    public void printClearExpenses(ArrayList<Expense> expenses) {
        ArrayList<String> msgs = new ArrayList<String>();

        msgs.add(Messages.EXPENSE_CLEAR_SUCCESSFUL.toString());
        
        msgs.addAll(printExpenses(expenses));
        ui.printMessage(msgs.toArray(new String[msgs.size()]));
    }

    /**
     * Prints expenses
     * @param expenses Arraylist containing all expenses
     * @return Arraylist with messages to be printed
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
