package seedu.duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.action.ExpenseAction;
import seedu.duke.exception.BBException;
import seedu.duke.exception.CommandActionExecuteInvalidException;
import seedu.duke.model.Budget;
import seedu.duke.model.Expense;
import seedu.duke.util.Constants;
import seedu.duke.util.Pair;

//@@author tzixi
public class ExpenseCommand extends Command {
    private static final DateTimeFormatter formatter = DateTimeFormatter
        .ofPattern(Constants.ACCEPTABLE_DATE_FORMAT.toString());

    // Format
    private static final String[] ACTIONS = {"add", "del", "list"};
    private static final Pair[][] ACTIONS_REQUIRED_PARAMS = {
        { new Pair("/c", String.class), new Pair("/n", String.class), new Pair("/a", double.class) },
        { new Pair("/n", int.class) },
        { },
    };
    
    private static final Pair[][] ACTIONS_OPTIONAL_PARAMS = {
        { new Pair("/d", LocalDate.class) },
        {},
        { new Pair("/c", String.class), new Pair("/f", LocalDate.class), new Pair("/t", LocalDate.class) }
    };

    public ExpenseCommand() {
        super(CommandEnum.EXPENSE, ACTIONS, ACTIONS_REQUIRED_PARAMS, ACTIONS_OPTIONAL_PARAMS);
    }

    @Override
    public void execute(Data data, Ui ui) throws BBException {
        ArrayList<Expense> expenses = data.getExpenses();
        ExpenseAction expenseAction = new ExpenseAction(expenses, ui);

        switch (action) {
        case "add":
            ArrayList<Budget> budgets = data.getBudgets();
            executeAddExpense(expenseAction, requiredParams, optionalParams, budgets);
            break;
        case "del":
            executeDelExpense(expenseAction, requiredParams);
            break;
        case "list":
            executeListExpense(expenseAction, optionalParams);
            break;
        default:
            throw new CommandActionExecuteInvalidException();
        }

        data.exportData();
    }

    private void executeAddExpense(ExpenseAction expenseAction, String[] requiredParams,
        String[] optionalParams, ArrayList<Budget> budgets) throws BBException {
        String expenseCategory = requiredParams[0];
        String expenseName = requiredParams[1];
        Double expenseAmount = Double.parseDouble(requiredParams[2]);

        LocalDate expenseDate;
        if (optionalParams[0] == null) {
            expenseDate = LocalDate.now();
        } else {
            expenseDate = LocalDate.parse(optionalParams[0], formatter);
        }

        expenseAction.addExpense(expenseCategory, expenseName, expenseAmount, expenseDate, budgets);
    }

    private void executeDelExpense(ExpenseAction expenseAction, String[] requiredParams) throws BBException {
        int expenseNo = Integer.parseInt(requiredParams[0]);
        expenseAction.deleteExpense(expenseNo);
    }

    private void executeListExpense(ExpenseAction expenseAction, String[] requiredParams) throws BBException {
        String expenseCategory = optionalParams[0];
        String expenseFromString = optionalParams[1];
        String expenseToString = optionalParams[2];

        if (expenseFromString == null && expenseToString == null) {
            expenseAction.listExpenses(expenseCategory);
            return;
        }

        LocalDate expenseFrom = null;
        LocalDate expenseTo = null;

        if (expenseFromString != null) {
            expenseFrom = LocalDate.parse(expenseFromString, formatter);
        }

        if (expenseToString != null) {
            expenseTo = LocalDate.parse(expenseToString, formatter);
        }

        expenseAction.listExpensesRange(expenseFrom, expenseTo, expenseCategory);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
