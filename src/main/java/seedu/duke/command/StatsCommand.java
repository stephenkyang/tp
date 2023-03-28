package seedu.duke.command;

import java.time.LocalDate;

import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.action.StatsAction;
import seedu.duke.exception.BBException;
import seedu.duke.exception.CommandActionExecuteInvalidException;
import seedu.duke.exception.StatsInvalidOptionsException;
import seedu.duke.util.Pair;

//@@author SaiChaitanya13
public class StatsCommand extends Command {

    // Format
    private static String[] ACTIONS = {"show", "help"};
    private static Pair[][] ACTIONS_REQUIRED_PARAMS = {{},{}};
    private static Pair[][] ACTIONS_OPTIONAL_PARAMS = {

        {new Pair("/m", int.class), new Pair("/y", int.class), new Pair ("/v", String.class)},
        {}
    };

    public StatsCommand() {
        super(CommandEnum.STATS, ACTIONS, ACTIONS_REQUIRED_PARAMS, ACTIONS_OPTIONAL_PARAMS);
    }

    /**
     * Executes different functions based on stats action
     *
     * @param data Stored information regarding the different transactions
     * @param ui User interface of the application
     * @throws BBException For unknown and invalid inputs
     */
    @Override
    public void execute(Data data, Ui ui) throws BBException {
        StatsAction statsAction = new StatsAction(data, ui);

        switch (action) {
        case "show":
            executeShowStats(statsAction, optionalParams);
            break;
        case "help":
            executeHelpStats(statsAction);
            break;
        default:
            throw new CommandActionExecuteInvalidException();
        }
    }


    /**
     * Parses the optional attributes such as month, year, verbose options,
     * which will be used to execute show stats in the action class.
     * If neither month and year is specified, use the current's month and year.
     * 
     * @param statsAction action selected will be executed through action class
     * @param optionalParams parameters containing the optional attributes
     * @throws BBException for any error thrown in the action class

     */
    private void executeShowStats(StatsAction statsAction, String[] optionalParams) throws BBException {
        // if year is not provided, use current year
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();
        boolean showDeposit = false;
        boolean showExpense = false;

        if (optionalParams[0] != null) {
            month = Integer.parseInt(optionalParams[0]);
        }
        if (optionalParams[1] != null) {
            year = Integer.parseInt(optionalParams[1]);
        }

        // verbose for deposit and expense
        if (optionalParams[2] != null) {
            String verboseOptions = optionalParams[2];
            if (!verboseOptions.matches("[de]+")) {
                throw new StatsInvalidOptionsException();
            }

            showDeposit = verboseOptions.contains("d");
            showExpense = verboseOptions.contains("e");
        }

        statsAction.showStats(month, year, showDeposit, showExpense);
    }

    /**
     * Executes stats help command
     *
     * @param statsAction Object of class StatsAction
     */
    private void executeHelpStats(StatsAction statsAction) {
        statsAction.statsHelp();
    }

    /**
     * Function to show program should not exit
     * @return false --> Program should not exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
