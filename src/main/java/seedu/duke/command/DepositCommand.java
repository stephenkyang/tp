package seedu.duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.action.DepositAction;
import seedu.duke.exception.BBException;
import seedu.duke.exception.CommandActionExecuteInvalidException;
import seedu.duke.model.Deposit;
import seedu.duke.util.Constants;
import seedu.duke.util.Pair;

//@@author stephenkyang
public class DepositCommand extends Command {
    private static final DateTimeFormatter formatter = DateTimeFormatter
        .ofPattern(Constants.ACCEPTABLE_DATE_FORMAT.toString());
    
    // Format
    private static final String[] ACTIONS = {"add", "del", "list"};
    private static final Pair[][] ACTIONS_REQUIRED_PARAMS = {
        { new Pair("/n", String.class), new Pair("/a", double.class) },
        { new Pair("/n", int.class) },
        {}
    };
    private static final Pair[][] ACTIONS_OPTIONAL_PARAMS = {
        { new Pair("/d", LocalDate.class) },
        {},
        {}
    };

    public DepositCommand() {
        super(CommandEnum.DEPOSIT, ACTIONS, ACTIONS_REQUIRED_PARAMS, ACTIONS_OPTIONAL_PARAMS);
    }

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
        case "list":
            executeListDeposit(depositAction);
            break;
        default:
            throw new CommandActionExecuteInvalidException();
        }

        data.exportData();
    }

    private void executeAddDeposit(DepositAction depositAction, String[] requiredParams, String[] optionalParams) {
        String depositName = requiredParams[0];
        Double depositAmount = Double.parseDouble(requiredParams[1]);

        // LocalDate depositDate = LocalDate.parse(optionalParams[0], formatter);
        LocalDate depositDate;
        if (optionalParams[0] == null) {
            depositDate = LocalDate.now();
        } else {
            depositDate = LocalDate.parse(optionalParams[0], formatter);
        }

        depositAction.addDeposit(depositName, depositAmount, depositDate);
    }

    private void executeDelDeposit(DepositAction depositAction, String[] requiredParams) throws BBException {
        int depositNo = Integer.parseInt(requiredParams[0]);
        depositAction.deleteDeposit(depositNo);
    }

    private void executeListDeposit(DepositAction depositAction) {
        depositAction.printDeposits();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
