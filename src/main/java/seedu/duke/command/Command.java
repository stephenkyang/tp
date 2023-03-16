package seedu.duke.command;

import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.exception.BBException;
import seedu.duke.util.Pair;

//@@author pinyoko573
/**
 * Provides the abstract class for mulitple subclasses
 * (BudgetCommand, DepositCommand, etc.).
 * In each subclass, the names of actions and each required
 * and optional parameters MUST be specified.
 * When the user inputs the command, action and required/optional
 * params, it is assigned to each variable which will be used for
 * execution. 
 */
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

    public abstract void execute(Data data, Ui ui) throws BBException;
    public abstract boolean isExit();

    /**
     * Gets the name of the command
     * @return name of the command
     */
    public CommandEnum getCommandName() {
        return this.name;
    }

    /**
     * Gets the actions that are available for a command.
     * @return array of actions that are available
     */
    public String[] getActions() {
        return this.actions;
    }

    /**
     * Gets the action that is selected from CommandParser
     * @return the action that is selected
     */
    public String getAction() {
        return this.action;
    }

    /**
     * Gets the array element no. based from action name from array of actions.
     * This method is used to retrieve the required/optional params based on the
     * action number.
     * @param action action name that is going to be executed
     * @return the element no. of the action
     */
    public int getActionNo(String action) {
        for (int i = 0; i < actions.length; i++) {
            if (action.equals(actions[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets the required parameters needed based from the action.
     * @param no element no. of the action taken from getActionNo
     * @return array of required parameters of an action
     */
    public Pair[] getRequiredParams(int no) {
        return requiredParamsList[no];
    }

    /**
     * Gets the optional parameters needed based from the action.
     * @param no element no. of the action taken from getActionNo
     * @return array of optional parameters of an action
     */
    public Pair[] getOptionalParams(int no) {
        return optionalParamsList[no];
    }

    /**
     * Sets the action of a command before execution.
     * @param action Name of action (e.g. add)
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Sets the parameters of a command before execution.
     * @param requiredParams Array of required parameters
     * @param optionalParams Array of optional parameters
     */
    public void setParams(String[] requiredParams, String[] optionalParams) {
        this.requiredParams = requiredParams;
        this.optionalParams = optionalParams;
    }
}
