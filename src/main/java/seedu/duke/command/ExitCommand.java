package seedu.duke.command;

import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.util.Pair;

public class ExitCommand extends Command {
    // Format
    private static final String[] ACTIONS = {};
    private static final Pair[][] ACTIONS_REQUIRED_PARAMS = {};
    private static final Pair[][] ACTIONS_OPTIONAL_PARAMS = {};
    
    public ExitCommand() {
        super(CommandEnum.EXIT, ACTIONS, ACTIONS_REQUIRED_PARAMS, ACTIONS_OPTIONAL_PARAMS);
    }

    @Override
    public void execute(Data data, Ui ui) {
    }

    @Override
    public boolean isExit() {
        return true;
    }
    
}
