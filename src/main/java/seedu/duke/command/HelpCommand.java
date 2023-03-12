package seedu.duke.command;

import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.util.Pair;

public class HelpCommand extends Command{


    private static final String[] ACTIONS = {};
    private static final Pair[][] ACTIONS_REQUIRED_PARAMS = {};
    private static final Pair[][] ACTIONS_OPTIONAL_PARAMS = {};

    public HelpCommand() {
        super(CommandEnum.EXIT, ACTIONS, ACTIONS_REQUIRED_PARAMS, ACTIONS_OPTIONAL_PARAMS);
    }

    @Override
    public void execute(Data data, Ui ui) {
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints out the different commands and features of the app
     */
    public static void showCommands(){
        System.out.println("Budget Buddy helps you to mange your finances better.");
        System.out.println("Budget Buddy has 3 main categories:");
        System.out.println("1. Budget");
        System.out.println("   - Choose how much money you want to allocate to a budget of your specified name");
        System.out.println("2. Expense ");
        System.out.println("   - track how much money you have spent, and link it to a certain budget of yours");
        System.out.println("3. Deposit ");
        System.out.println("   - track how much money you have earned or received");
        System.out.println("To learn the specific commands for each category, input \"(catergory) help\"");

    }

}
