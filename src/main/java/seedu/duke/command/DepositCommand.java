package seedu.duke.command;

import java.time.LocalDate;
import java.util.ArrayList;

import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.action.DepositAction;
import seedu.duke.exception.BBException;
import seedu.duke.exception.CommandActionExecuteInvalidException;
import seedu.duke.model.Deposit;
import seedu.duke.util.Commons;
import seedu.duke.util.Constants;
import seedu.duke.util.Pair;

//@@author stephenkyang
public class DepositCommand extends Command {    
    // Format for Deposit command.
    // Actions, required and optional parameters (w/ data type) of each action are specified.
    private static final String[] ACTIONS = {"add", "del", "find", "list", "clear", "help"};
    private static final Pair[][] ACTIONS_REQUIRED_PARAMS = {
        { new Pair("/n", String.class), new Pair("/a", double.class) },
        { new Pair("/n", int.class) },
        { new Pair("/n", String.class) },
        {},
        {},
        {}
    };
    private static final Pair[][] ACTIONS_OPTIONAL_PARAMS = {
        { new Pair("/d", LocalDate.class) },
        {},
        {},
        { new Pair("/f", LocalDate.class), new Pair("/t", LocalDate.class) },
        { new Pair("/f", LocalDate.class), new Pair("/t", LocalDate.class) },
        {}
    };

    public DepositCommand() {
        super(CommandEnum.DEPOSIT, ACTIONS, ACTIONS_REQUIRED_PARAMS, ACTIONS_OPTIONAL_PARAMS);
    }

    /**
     * Executes the command. Action, required and optional parameters are 
     * previously set by CommandParser parse. Execution of the action depends
     * on the action name.
     * 
     * @param data  Data containing budget, deposit and expense info
     * @param ui    For printing messages through Ui object
     * @throws BBException for any error thrown in the action class
     */
    @Override
    public void execute(Data data, Ui ui) throws BBException {
        ArrayList<Deposit> depositList = data.getDeposits();
        DepositAction depositAction = new DepositAction(depositList, ui);
        switch (action) {
        case "add":
            executeAddDeposit(depositAction, requiredParams, optionalParams);
            break;
        case "del":
            executeDelDeposit(depositAction, requiredParams);
            break;
        case "find":
            executeFindDeposit(depositAction, requiredParams);
            break;
        case "list":
            executeListDeposit(depositAction, optionalParams);
            break;
        case "clear":
            executeClearDeposit(depositAction, optionalParams);
            break;
        case "help":
            executeHelpDeposit(depositAction);
            break;
        default:
            throw new CommandActionExecuteInvalidException();
        }

        data.exportData();
    }

    private void executeHelpDeposit(DepositAction depositAction) {
        depositAction.depositHelp();
    }

    /**
     * Parses the required attributes such as deposit name, amount,
     * and optional attributes such as date
     * which will be used to execute add deposit in the action class.
     * If date is not specified, use today's date.
     * 
     * @param depositAction action selected will be executed through action class
     * @param requiredParams parameters containing the required attributes
     * @param optionalParams parameters containing the optional attributes
     * @throws BBException for any error thrown in the action class
     */
    private void executeAddDeposit(DepositAction depositAction, String[] requiredParams,
        String[] optionalParams) throws BBException {
            
        String depositName = requiredParams[0];
        Double depositAmount = Double.parseDouble(requiredParams[1]);

        LocalDate depositDate;
        String depositDateString = optionalParams[0];
        if (depositDateString == null) {
            depositDate = LocalDate.now();
        } else {
            depositDate = LocalDate.parse(depositDateString, Constants.ACCEPTABLE_DATE_FORMAT);
        }

        depositAction.addDeposit(depositName, depositAmount, depositDate);
    }

    /**
     * Parses the required attributes such as deposit no,
     * which will be used to execute del deposit in the action class.
     * 
     * @param depositAction action selected will be executed through action class
     * @param requiredParams parameters containing the required attributes
     * @throws BBException for any error thrown in the action class
     */
    private void executeDelDeposit(DepositAction depositAction, String[] requiredParams) throws BBException {
        int depositId = Integer.parseInt(requiredParams[0]);
        depositAction.deleteDeposit(depositId);
    }

    /**
     * Parses the required attributes such as deposit name
     * which will be used to execute find deposit in the action class.
     * 
     * @param depositAction action selected will be executed through action class
     * @param requiredParams parameters containing the required attributes
     * @throws BBException for any error thrown in the action class
     */
    private void executeFindDeposit(DepositAction depositAction, String[] requiredParams) throws BBException {
        String depositName = requiredParams[0];
        depositAction.findDeposits(depositName);
    }

    /**
     * Parses optional attributes such as from and to date,
     * which will be used to execute clear deposit in the action class.
     * 
     * @param depositAction action selected will be execute through action class
     * @param optionalParams parameters containing the optional attributes
     * @throws BBException for any error thrown in the action class
     */
    private void executeClearDeposit(DepositAction depositAction, String[] optionalParams) throws BBException {
        String depositFromString = optionalParams[0];
        String depositToString = optionalParams[1];

        LocalDate[] dates = Commons.parseDateRange(depositFromString, depositToString);
        LocalDate depositFrom = dates[0];
        LocalDate depositTo = dates[1];

        depositAction.clearDeposits(depositFrom, depositTo);
    }

    /**
     * Parses the optional attributes such as from and to date,
     * which will be used to execute list deposit in the action class.
     * If neither dates are specified, list all the deposits.
     * If either or both dates are specified, filter deposits by date.
     * 
     * @param depositAction action selected will be executed through action class
     * @param optionalParams parameters containing the optional attributes
     * @throws BBException for any error thrown in the action class
     */
    private void executeListDeposit(DepositAction depositAction, String[] optionalParams) throws BBException {
        String depositFromString = optionalParams[0];
        String depositToString = optionalParams[1];

        if (depositFromString == null && depositToString == null) {
            depositAction.listDeposits();
            return;
        }

        LocalDate[] dates = Commons.parseDateRange(depositFromString, depositToString);
        LocalDate depositFrom = dates[0];
        LocalDate depositTo = dates[1];

        depositAction.listDepositsRange(depositFrom, depositTo);
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
