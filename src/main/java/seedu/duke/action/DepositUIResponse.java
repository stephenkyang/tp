package seedu.duke.action;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import seedu.duke.Ui;
import seedu.duke.model.Deposit;
import seedu.duke.util.Constants;
import seedu.duke.util.Messages;

//@@author stephenkyang
/**
 * Contains User Interface text responses when a deposit method is run
 */
public class DepositUIResponse {
    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern(Constants.OUTPUT_DATE_FORMAT.toString());

    private Ui ui;

    public DepositUIResponse(Ui ui) {
        this.ui = ui;
    }

    public void printDepositAddSuccessful(Deposit deposit) {
        String msg = String.format(Messages.DEPOSIT_DEPOSIT.toString(), deposit.getId(), deposit.getName(),
            deposit.getAmount(), deposit.getDate().format(fmt));
        ui.printMessage(Messages.DEPOSIT_ADD_SUCCESSFUL.toString(), msg);
    }

    public void printDepositDelSuccessful(Deposit deposit) {
        String msg = String.format(Messages.DEPOSIT_DEPOSIT.toString(), deposit.getId(), deposit.getName(),
            deposit.getAmount(), deposit.getDate().format(fmt));
        ui.printMessage(Messages.DEPOSIT_DELETE_SUCCESSFUL.toString(), msg);
    }

    public void printListDeposits(ArrayList<Deposit> previousDeposits, ArrayList<Deposit> currentDeposits) {
        if (previousDeposits.size() == 0 && currentDeposits.size() == 0) {
            ui.printMessage(Messages.DEPOSIT_LIST_NOTHING.toString());
            return;
        }

        ArrayList<String> msgs = new ArrayList<String>();

        if (previousDeposits.size() != 0) {
            msgs.add(Messages.DEPOSIT_LIST_PREVIOUS.toString());
            msgs.addAll(printDeposits(previousDeposits));
        }

        msgs.add("");

        if (currentDeposits.size() != 0) {
            msgs.add(Messages.DEPOSIT_LIST_CURRENT.toString());
            msgs.addAll(printDeposits(currentDeposits));
        }

        ui.printMessage(msgs.toArray(new String[msgs.size()]));
    }

    public static ArrayList<String> printDeposits(ArrayList<Deposit> deposits) {
        ArrayList<String> msgs = new ArrayList<String>();
        for (Deposit d : deposits) {
            String msg = String.format(Messages.DEPOSIT_DEPOSIT.toString(), d.getId(), d.getName(), d.getAmount(),
                d.getDate().format(fmt));
            msgs.add(msg);
        }

        return msgs;
    }
}
