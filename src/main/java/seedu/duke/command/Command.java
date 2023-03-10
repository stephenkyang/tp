package seedu.duke.command;

import seedu.duke.Data;
import seedu.duke.util.Pair;

public abstract class Command {
    // Format
    protected String[] actions;
    protected Pair[][] requiredParamsList;
    protected Pair[][] optionalParamsList;

    protected CommandEnum name;
    protected String action;
    protected String[] requiredParams;
    protected String[] optionalParams;

    public Command(CommandEnum name, String[] actions, Pair[][] requiredParamsList, Pair[][] optionalParamsList) {
        this.name = name;
        this.actions = actions;
        this.requiredParamsList = requiredParamsList;
        this.optionalParamsList = optionalParamsList;
    }

    public CommandEnum getCommandName() {
        return this.name;
    }

    public String[] getActions() {
        return this.actions;
    }

    public String getAction() {
        return this.action;
    }

    public int getActionNo(String action) {
        for (int i = 0; i < actions.length; i++) {
            if (action.equals(actions[i])) {
                return i;
            }
        }
        return -1;
    }

    public Pair[] getRequiredParams(int no) {
        return requiredParamsList[no];
    }

    public Pair[] getOptionalParams(int no) {
        return optionalParamsList[no];
    }

    public void set(String action, String[] requiredParams, String[] optionalParams) {
        this.action = action;
        this.requiredParams = requiredParams;
        this.optionalParams = optionalParams;
    }

    public abstract void execute(Data data);
    public abstract boolean isExit();
}
