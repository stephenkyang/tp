package seedu.duke.command;

import seedu.duke.utils.Pair;

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

    public void execute() {
        if (action == "add") {
            
        } else if (action == "set") {

        } else if (action == "del") {

        } else if (action == "list") {

        }
    }
    

}
