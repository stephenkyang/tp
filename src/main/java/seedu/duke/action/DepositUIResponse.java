package seedu.duke.action;

import java.util.ArrayList;

import seedu.duke.Ui;
import seedu.duke.model.Deposit;
import seedu.duke.util.Messages;

//@@author stephenkyang
/**
 * Contains User Interface text responses when a deposit method is run
 */
public class DepositUIResponse {
    private Ui ui;

    public DepositUIResponse(Ui ui) {
        this.ui = ui;
    }

    public void printDepositAddSuccessful(Deposit deposit, int count) {
        String msg = String.format(Messages.DEPOSIT_ADD_SUCCESSFUL.toString(), deposit.getName(), deposit.getAmount());
        String countMsg = String.format(Messages.DEPOSIT_NUMBER_OF.toString(), count);
        ui.printMessage(msg, countMsg);
    }

    public void printListDeposits(ArrayList<Deposit> deposits) {
        ArrayList<String> msgs = new ArrayList<String>();
        msgs.add(Messages.DEPOSIT_PRINT.toString());
        int i = 1;
        for (Deposit d : deposits) {
            if (d != null) {
                String msg = String.format(Messages.DEPOSIT_PRINT_DEPOSIT.toString(), i, d.getName(), d.getAmount());
                msgs.add(msg);
                i++;
            }
        }

        ui.printMessage(msgs.toArray(new String[0]));
    }
}
