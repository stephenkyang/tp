package seedu.duke.command;

import java.util.ArrayList;

import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.action.ExpenseAction;
import seedu.duke.exception.BBException;
import seedu.duke.model.Expense;
import seedu.duke.util.Pair;

public class ExpenseCommand extends Command {
    // Format
    private static final String[] ACTIONS = {"add", "del", "list"};
    private static final Pair[][] ACTIONS_REQUIRED_PARAMS = {
        { new Pair("/c", String.class), new Pair("/n", String.class), new Pair("/a", double.class),
            new Pair("/d", String.class) },
        { new Pair("/n", int.class) },
        { },
    };
    // will use this later for tP 1.1
    private static final Pair[][] ACTIONS_OPTIONAL_PARAMS = {};

    public ExpenseCommand() {
        super(CommandEnum.EXPENSE, ACTIONS, ACTIONS_REQUIRED_PARAMS, ACTIONS_OPTIONAL_PARAMS);
    }

    @Override
    public void execute(Data data, Ui ui) throws BBException {
        ArrayList<Expense> expenseList = data.expenseList;
        ExpenseAction expenseAction = new ExpenseAction(expenseList, ui);

        switch (action) {
        case "add":
            executeAddExpense(expenseAction, requiredParams);
            break;
        case "del":
            executeDelExpense(expenseAction, requiredParams);
            break;
        case "list":
            executeListExpense(expenseAction);
            break;
        default:
            // HANDLE DEFAULT HERE
        }
    }

    private void executeAddExpense(ExpenseAction expenseAction, String[] requiredParams) {
        String expenseCategory = requiredParams[0];
        String expenseName = requiredParams[1];
        Double expenseAmount = Double.parseDouble(requiredParams[2]);
        String expenseDate = requiredParams[3];

        expenseAction.addExpense(expenseCategory, expenseName, expenseAmount, expenseDate);
    }

    private void executeDelExpense(ExpenseAction expenseAction, String[] requiredParams) throws BBException {
        int expenseNo = Integer.parseInt(requiredParams[0]);
        expenseAction.deleteExpense(expenseNo);
    }

    private void executeListExpense(ExpenseAction expenseAction) {
        expenseAction.printExpenses();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
