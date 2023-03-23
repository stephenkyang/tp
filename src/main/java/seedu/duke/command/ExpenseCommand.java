package seedu.duke.command;

import java.time.LocalDate;
import java.util.ArrayList;

import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.action.ExpenseAction;
import seedu.duke.exception.BBException;
import seedu.duke.exception.CommandActionExecuteInvalidException;
import seedu.duke.model.Budget;
import seedu.duke.model.Expense;
import seedu.duke.util.Commons;
import seedu.duke.util.Constants;
import seedu.duke.util.Pair;

//@@author tzixi
public class ExpenseCommand extends Command {
    // Format
    private static final String[] ACTIONS = {"add", "del", "find", "list", "clear", "help"};
    private static final Pair[][] ACTIONS_REQUIRED_PARAMS = {
        { new Pair("/c", String.class), new Pair("/n", String.class), new Pair("/a", double.class) },
        { new Pair("/n", int.class) },
        { new Pair("/n", String.class) },
        {},
        {},
        {}
    };
    
    private static final Pair[][] ACTIONS_OPTIONAL_PARAMS = {
        { new Pair("/d", LocalDate.class) },
        {},
        {},
        { new Pair("/c", String.class), new Pair("/f", LocalDate.class), new Pair("/t", LocalDate.class) },
        { new Pair("/c", String.class), new Pair("/f", LocalDate.class), new Pair("/t", LocalDate.class) },
        {}
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
        case "find":
            executeFindExpense(expenseAction, requiredParams);
            break;
        case "list":
            executeListExpense(expenseAction, optionalParams);
            break;
        case "clear":
            executeClearExpense(expenseAction, optionalParams);
            break;
        case "help":
            executeHelpExpense(expenseAction);
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
            expenseDate = LocalDate.parse(optionalParams[0], Constants.ACCEPTABLE_DATE_FORMAT);
        }

        expenseAction.addExpense(expenseCategory, expenseName, expenseAmount, expenseDate, budgets);
    }

    private void executeDelExpense(ExpenseAction expenseAction, String[] requiredParams) throws BBException {
        int expenseNo = Integer.parseInt(requiredParams[0]);
        expenseAction.deleteExpense(expenseNo);
    }

    private void executeFindExpense(ExpenseAction expenseAction, String[] requiredParams) throws BBException {
        String expenseName = requiredParams[0];
        expenseAction.findExpenses(expenseName);
    }

    private void executeListExpense(ExpenseAction expenseAction, String[] optionalParams) throws BBException {
        String expenseCategory = optionalParams[0];
        String expenseFromString = optionalParams[1];
        String expenseToString = optionalParams[2];

        if (expenseFromString == null && expenseToString == null) {
            expenseAction.listExpenses(expenseCategory);
            return;
        }

        LocalDate[] dates = Commons.parseDateRange(expenseFromString, expenseToString);
        LocalDate expenseFrom = dates[0];
        LocalDate expenseTo = dates[1];

        expenseAction.listExpensesRange(expenseFrom, expenseTo, expenseCategory);
    }

    private void executeClearExpense(ExpenseAction expenseAction, String[] optionalParams) throws BBException {
        String expenseCategory = optionalParams[0];
        String expenseFromString = optionalParams[1];
        String expenseToString = optionalParams[2];

        LocalDate[] dates = Commons.parseDateRange(expenseFromString, expenseToString);
        LocalDate expenseFrom = dates[0];
        LocalDate expenseTo = dates[1];

        expenseAction.clearExpenses(expenseFrom, expenseTo, expenseCategory);
    }

    private void executeHelpExpense(ExpenseAction expenseAction) {
        expenseAction.expenseHelp();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
