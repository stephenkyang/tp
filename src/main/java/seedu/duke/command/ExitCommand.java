package seedu.duke.command;

import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.util.Pair;

//@@author pinyoko573
/**
 * When exit command is inputted by the user, it will
 * return true to the main function, which will exit
 * the application.
 */
public class ExitCommand extends Command {
    // Format for Exit command. No actions required.
    private static final String[] ACTIONS = {};
    private static final Pair[][] ACTIONS_REQUIRED_PARAMS = {};
    private static final Pair[][] ACTIONS_OPTIONAL_PARAMS = {};
    
    public ExitCommand() {
        super(CommandEnum.EXIT, ACTIONS, ACTIONS_REQUIRED_PARAMS, ACTIONS_OPTIONAL_PARAMS);
    }

    @Override
    public void execute(Data data, Ui ui) {
    }

    /**
     * After execution of each command in main, it will check
     * if the action specified wants to exit the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
    
}
