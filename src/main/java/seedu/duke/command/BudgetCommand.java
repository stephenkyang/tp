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

    private static final String[] ACTIONS = {"add", "set", "del", "list", "help"};
    private static final Pair[][] ACTIONS_REQUIRED_PARAMS = {
            {new Pair("/c", String.class), new Pair("/a", double.class)},
            {new Pair("/c", String.class), new Pair("/a", double.class)},
            {new Pair("/c", String.class)},
            {},
            {},
    };
    private static final Pair[][] ACTIONS_OPTIONAL_PARAMS = {
            {},
            {},
            {},
            {new Pair("/m", int.class), new Pair("/y", int.class)},
            {},
    };

    public BudgetCommand() {
        super(CommandEnum.BUDGET, ACTIONS, ACTIONS_REQUIRED_PARAMS, ACTIONS_OPTIONAL_PARAMS);
    }

    /**
     * Executes the command. Action, required and optional parameters are
     * previously set by CommandParser parse. Execution of the action depends
     * on the action name.
     *
     * @param data Data containing budget, deposit and expense info
     * @param ui   For printing messages through Ui object
     * @throws BBException for any error thrown in the action class
     */
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
        case "help":
            executeHelpBudget(budgetAction);
            break;
        default:
            throw new CommandActionExecuteInvalidException();
        }

        data.exportData();
    }

    private void executeHelpBudget(BudgetAction budgetAction) {
        budgetAction.budgetHelp();
    }

    /**
     * Parses the required attributes such as budget name, limit,
     * which will be used to execute add budget in the action class.
     *
     * @param budgetAction   action selected will be execute through action class
     * @param requiredParams parameters containing the required attributes
     */
    private void executeAddBudget(BudgetAction budgetAction, String[] requiredParams) {
        String budgetName = requiredParams[0];
        Double budgetLimit = Double.parseDouble(requiredParams[1]);
        budgetAction.addBudget(budgetName, budgetLimit);
    }

    /**
     * Parses the required attributes such as budget name, limit,
     * which will be used to execute set budget in the action class.
     *
     * @param budgetAction   action selected will be execute through action class
     * @param requiredParams parameters containing the required attributes
     */
    private void executeSetBudget(BudgetAction budgetAction, String[] requiredParams) {
        String budgetName = requiredParams[0];
        Double budgetLimit = Double.parseDouble(requiredParams[1]);
        budgetAction.setBudget(budgetName, budgetLimit);
    }

    /**
     * Parses the required attributes such as budget name,
     * which will be used to execute del budget in the action class.
     *
     * @param budgetAction   action selected will be execute through action class
     * @param requiredParams parameters containing the required attributes
     * @param expenses       Expense data that will be used to delete if category is deleted
     */
    private void executeDelBudget(BudgetAction budgetAction, String[] requiredParams, ArrayList<Expense> expenses) {
        String budgetName = requiredParams[0];
        budgetAction.deleteBudget(budgetName, expenses);
    }

    /**
     * Parses the optional attributes such as month, year,
     * which will be used to execute list budget in the action class.
     * If neither month and year is specified, use the current's month and year.
     *
     * @param budgetAction   action selected will be execute through action class
     * @param optionalParams parameters containing the optional attributes
     * @param expenses       Expense data that will be used to calculate the expense for each category
     */
    private void executeListBudget(BudgetAction budgetAction, String[] optionalParams,
                                   ArrayList<Expense> expenses) throws BBException {
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
