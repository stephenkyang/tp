package seedu.duke.command;

import java.util.ArrayList;

import seedu.duke.Data;
import seedu.duke.action.BudgetAction;
import seedu.duke.model.Budget;
import seedu.duke.util.Pair;

public class BudgetCommand extends Command {
    // Format
    private static final String[] ACTIONS = {"add", "set", "del", "list"};
    private static final Pair[][] ACTIONS_REQUIRED_PARAMS = {
        { new Pair("/c", String.class), new Pair("/l", Double.class) },
        { new Pair("/c", String.class), new Pair("/l", Double.class) },
        { new Pair("/c", String.class) },
        {}
    };
    private static final Pair[][] ACTIONS_OPTIONAL_PARAMS = {};

    public BudgetCommand() {
        super(CommandEnum.BUDGET, ACTIONS, ACTIONS_REQUIRED_PARAMS, ACTIONS_OPTIONAL_PARAMS);
    }

    public void execute(Data data) {
        ArrayList<Budget> budgetList = data.budgetList;
        BudgetAction budgetAction = new BudgetAction(budgetList);

        switch (action) {
        case "add":
            executeAddBudget(budgetAction, requiredParams);
            break;
        case "set":
            executeSetBudget(budgetAction, requiredParams);
            break;
        case "del":
            executeDelBudget(budgetAction, requiredParams);
            break;
        case "list":
            executeListBudget(budgetAction);
            break;
        default:
            // HANDLE DEFAULT HERE
        }
    }

    private void executeAddBudget(BudgetAction budgetAction, String[] requiredParams) {
        String budgetName = requiredParams[0];
        Double budgetLimit = Double.parseDouble(requiredParams[1]);
        budgetAction.addBudget(budgetName, budgetLimit);
    }

    private void executeSetBudget(BudgetAction budgetAction, String[] requiredParams) {
        String budgetName = requiredParams[0];
        Double budgetLimit = Double.parseDouble(requiredParams[1]);
        budgetAction.setBudget(budgetName, budgetLimit);
    }

    private void executeDelBudget(BudgetAction budgetAction, String[] requiredParams) {
        String budgetName = requiredParams[0];
        budgetAction.deleteBudget(budgetName);
    }

    private void executeListBudget(BudgetAction budgetAction) {
        budgetAction.printBudgets();
    }
}
