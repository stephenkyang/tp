package seedu.duke.command;

import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.action.StatsAction;
import seedu.duke.exception.BBException;
import seedu.duke.util.Pair;

public class StatsCommand extends Command {
    // Format
    private static final String[] ACTIONS = {};
    private static final Pair[][] ACTIONS_REQUIRED_PARAMS = {};
    private static final Pair[][] ACTIONS_OPTIONAL_PARAMS = {};

    public StatsCommand() {
        super(CommandEnum.STATS, ACTIONS, ACTIONS_REQUIRED_PARAMS, ACTIONS_OPTIONAL_PARAMS);
    }

    @Override
    public void execute(Data data, Ui ui) throws BBException {
        StatsAction statsAction = new StatsAction(data, ui);
        statsAction.viewStats();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
