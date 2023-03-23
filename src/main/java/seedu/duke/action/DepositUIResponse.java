package seedu.duke.action;

import java.time.LocalDate;
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
    private Ui ui;

    public DepositUIResponse(Ui ui) {
        this.ui = ui;
    }
    
    public void printDepositCommands() {
        String msg = String.format(Messages.DEPOSIT_HELP_COMMANDS.toString());
        ui.printMessage(msg);
    }

    public void printDepositAddSuccessful(Deposit deposit) {
        String msg = String.format(Messages.DEPOSIT_DEPOSIT.toString(), deposit.getId(), deposit.getName(),
            deposit.getAmount(), deposit.getDate().format(Constants.OUTPUT_DATE_FORMAT));
        ui.printMessage(Messages.DEPOSIT_ADD_SUCCESSFUL.toString(), msg);
    }

    public void printDepositDelSuccessful(Deposit deposit) {
        String msg = String.format(Messages.DEPOSIT_DEPOSIT.toString(), deposit.getId(), deposit.getName(),
            deposit.getAmount(), deposit.getDate().format(Constants.OUTPUT_DATE_FORMAT));
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

        if (currentDeposits.size() != 0) {
            msgs.add(Messages.DEPOSIT_LIST_CURRENT.toString());
            msgs.addAll(printDeposits(currentDeposits));
        }

        ui.printMessage(msgs.toArray(new String[msgs.size()]));
    }

    public void printFindDeposits(ArrayList<Deposit> deposits) {
        if (deposits.size() == 0) {
            ui.printMessage(Messages.DEPOSIT_FIND_NOTHING.toString());
            return;
        }

        ArrayList<String> msgs = new ArrayList<String>();
        msgs.add(Messages.DEPOSIT_FIND.toString());
        msgs.addAll(printDeposits(deposits));

        ui.printMessage(msgs.toArray(new String[msgs.size()]));
    }

    // @@author pinyoko573
    public void printListDepositsRange(ArrayList<Deposit> deposits, LocalDate from, LocalDate to) {
        if (deposits.size() == 0) {
            ui.printMessage(Messages.DEPOSIT_LIST_NOTHING.toString());
            return;
        }

        ArrayList<String> msgs = new ArrayList<String>();

        String msg;
        if (from.equals(LocalDate.MIN)) {
            msg = String.format(Messages.DEPOSIT_LIST_RANGE_TO.toString(), to.format(Constants.OUTPUT_DATE_FORMAT));
        } else if (to.equals(LocalDate.MAX)) {
            msg = String.format(Messages.DEPOSIT_LIST_RANGE_FROM.toString(), from.format(Constants.OUTPUT_DATE_FORMAT));
        } else {
            msg = String.format(Messages.DEPOSIT_LIST_RANGE.toString(), from.format(Constants.OUTPUT_DATE_FORMAT),
                to.format(Constants.OUTPUT_DATE_FORMAT));
        }
        msgs.add(msg);

        msgs.addAll(printDeposits(deposits));
        ui.printMessage(msgs.toArray(new String[msgs.size()]));
    }

    // @@author stephenkyang
    public void printClearDeposits(ArrayList<Deposit> deposits) {
        if (deposits.size() == 0) {
            ui.printMessage(Messages.DEPOSIT_CLEAR_NOTHING.toString());   
            return; 
        }

        ArrayList<String> msgs = new ArrayList<String>();

        msgs.add(Messages.DEPOSIT_CLEAR_SUCCESSFUL.toString());
        
        msgs.addAll(printDeposits(deposits));
        ui.printMessage(msgs.toArray(new String[msgs.size()]));
    }

    public static ArrayList<String> printDeposits(ArrayList<Deposit> deposits) {
        ArrayList<String> msgs = new ArrayList<String>();
        for (Deposit d : deposits) {
            String msg = String.format(Messages.DEPOSIT_DEPOSIT.toString(), d.getId(), d.getName(), d.getAmount(),
                d.getDate().format(Constants.OUTPUT_DATE_FORMAT));
            msgs.add(msg);
        }

        return msgs;
    }
}
