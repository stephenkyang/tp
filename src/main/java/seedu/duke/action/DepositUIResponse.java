package seedu.duke.action;

import java.util.ArrayList;

import seedu.duke.Ui;
import seedu.duke.model.Deposit;
import seedu.duke.util.Messages;

/**
 * Contains User Interface text responses when a deposit method is run
 */
public class DepositUIResponse {
    private Ui ui;

    public DepositUIResponse(Ui ui) {
        this.ui = ui;
    }

    public void printListDeposits(ArrayList<Deposit> deposits) {
        ArrayList<String> msgs = new ArrayList<String>();
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
