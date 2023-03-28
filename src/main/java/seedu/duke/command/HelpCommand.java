package seedu.duke.command;

import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.util.Messages;
import seedu.duke.util.Pair;

//@@author chongyongrui
public class HelpCommand extends Command {
    // Format for Help command. No actions required.
    private static final String[] ACTIONS = {};
    private static final Pair[][] ACTIONS_REQUIRED_PARAMS = {};
    private static final Pair[][] ACTIONS_OPTIONAL_PARAMS = {};

    public HelpCommand() {
        super(CommandEnum.EXIT, ACTIONS, ACTIONS_REQUIRED_PARAMS, ACTIONS_OPTIONAL_PARAMS);
    }

    /**
     * Prints out the different commands and features of the app
     */
    @Override
    public void execute(Data data, Ui ui) {
        ui.printMessage(Messages.HELP_SHOW_COMMANDS.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
