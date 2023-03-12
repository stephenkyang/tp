package seedu.duke.command;

import java.util.ArrayList;

import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.action.DepositAction;
import seedu.duke.exception.BBException;
import seedu.duke.model.Deposit;
import seedu.duke.util.Pair;

public class DepositCommand extends Command {
    // Format
    private static final String[] ACTIONS = {"add", "del", "list"};
    private static final Pair[][] ACTIONS_REQUIRED_PARAMS = {
        { new Pair("/n", String.class), new Pair("/a", double.class) },
        { new Pair("/n", int.class) },
        {}, {}
    };
    private static final Pair[][] ACTIONS_OPTIONAL_PARAMS = {};

    public DepositCommand() {
        super(CommandEnum.DEPOSIT, ACTIONS, ACTIONS_REQUIRED_PARAMS, ACTIONS_OPTIONAL_PARAMS);
    }

    @Override
    public void execute(Data data, Ui ui) throws BBException {
        ArrayList<Deposit> depositList = data.depositList;
        DepositAction depositAction = new DepositAction(depositList, ui);

        switch (action) {
        case "add":
            executeAddDeposit(depositAction, requiredParams);
            break;
        case "del":
            executeDelDeposit(depositAction, requiredParams);
            break;
        case "list":
            executeListDeposit(depositAction);
            break;
        default:
            // HANDLE DEFAULT HERE
        }
    }

    private void executeAddDeposit(DepositAction depositAction, String[] requiredParams) {
        String depositName = requiredParams[0];
        Double depositAmount = Double.parseDouble(requiredParams[1]);
        depositAction.addDeposit(depositName, depositAmount);
    }

    private void executeDelDeposit(DepositAction depositAction, String[] requiredParams) throws BBException {
        int depositNo = Integer.parseInt(requiredParams[0]);
        depositAction.deleteDeposit(depositNo);
    }

    private void executeListDeposit(DepositAction budgetAction) {
        budgetAction.printDeposits();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
