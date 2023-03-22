package seedu.duke.command;

import java.time.LocalDate;
import java.util.ArrayList;

import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.action.BudgetAction;

import seedu.duke.exception.BBException;
import seedu.duke.exception.CommandActionExecuteInvalidException;
import seedu.duke.model.Budget;
import seedu.duke.model.Expense;
import seedu.duke.util.Pair;


//@@author chongyongrui
public class BudgetCommand extends Command {
    // Format
    private static final String[] ACTIONS = {"add", "set", "del", "list", "find", "help", "detail"};
    private static final Pair[][] ACTIONS_REQUIRED_PARAMS = {
            {new Pair("/c", String.class), new Pair("/l", double.class)},
            {new Pair("/c", String.class), new Pair("/l", double.class)},
            {new Pair("/c", String.class)},
            {},
            {new Pair("/c", String.class)},
            {},
            {new Pair("/c", String.class)}
    };
    private static final Pair[][] ACTIONS_OPTIONAL_PARAMS = {
            {},
            {},
            {},
            {new Pair("/m", int.class), new Pair("/y", int.class)},
            {},
            {},
            {}
    };

    public BudgetCommand() {
        super(CommandEnum.BUDGET, ACTIONS, ACTIONS_REQUIRED_PARAMS, ACTIONS_OPTIONAL_PARAMS);
    }

    @Override
    public void execute(Data data, Ui ui) throws BBException {
        ArrayList<Budget> budgetList = data.getBudgets();
        BudgetAction budgetAction = new BudgetAction(budgetList, ui);

        ArrayList<Expense> expenses = data.getExpenses();

        switch (action) {
        case "add":
            executeAddBudget(budgetAction, requiredParams);
            break;
        case "set":
            executeSetBudget(budgetAction, requiredParams);
            break;
        case "del":
            executeDelBudget(budgetAction, requiredParams, expenses);
            break;
        case "list":
            executeListBudget(budgetAction, optionalParams, expenses);
            break;
        // case "find":
        //     executeFindBudget(budgetAction, requiredParams);
        //     break;
        case "help":
            executeBudgetHelp(budgetAction);
            break;
        // case "detail":
        //     executeBudgetDetail(budgetAction, requiredParams, data.getExpenses());
        //     break;
        default:
            throw new CommandActionExecuteInvalidException();
        }

        data.exportData();
    }
    
    // private void executeBudgetDetail(BudgetAction budgetAction, String[] requiredParams,ArrayList<Expense> expenses){
    //     String budgetName = requiredParams[0];
    //     budgetAction.detailedBudget(budgetName, expenses);
    // }

    // private void executeFindBudget(BudgetAction budgetAction, String[] requiredParams) {
    //     String budgetName = requiredParams[0];
    //     budgetAction.findBudget(budgetName);
    // }

    private void executeBudgetHelp(BudgetAction budgetAction) {
        budgetAction.budgetHelp();
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


    private void executeDelBudget(BudgetAction budgetAction, String[] requiredParams, ArrayList<Expense> expenses) {
        String budgetName = requiredParams[0];
        budgetAction.deleteBudget(budgetName, expenses);
    }

    private void executeListBudget(BudgetAction budgetAction, String[] optionalParams,
        ArrayList<Expense> expenses) throws BBException {
        // int does not have null value, use -1 instead
        // if year is not provided, use current year
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();

        if (optionalParams[0] != null) {
            month = Integer.parseInt(optionalParams[0]);
        }
        if (optionalParams[1] != null) {
            year = Integer.parseInt(optionalParams[1]);
        }

        budgetAction.printBudgets(month, year, expenses);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
