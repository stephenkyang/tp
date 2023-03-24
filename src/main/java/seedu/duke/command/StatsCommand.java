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
    private static final String[] ACTIONS = {"show", "help"};
    private static final Pair[][] ACTIONS_REQUIRED_PARAMS = {{},{}};
    private static final Pair[][] ACTIONS_OPTIONAL_PARAMS = {
        {new Pair("/m", int.class), new Pair("/y", int.class), new Pair ("/v", String.class)},
        {}
    };

    public StatsCommand() {
        super(CommandEnum.STATS, ACTIONS, ACTIONS_REQUIRED_PARAMS, ACTIONS_OPTIONAL_PARAMS);
    }

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

    private void executeShowStats(StatsAction statsAction, String[] optionalParams) throws BBException {
        // int does not have null value, use -1 instead
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

    private void executeHelpStats(StatsAction statsAction) {
        statsAction.statsHelp();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
