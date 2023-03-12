package seedu.duke.action;

import java.util.ArrayList;

import seedu.duke.Ui;
import seedu.duke.exception.GlobalInvalidNumberException;
import seedu.duke.model.Deposit;
import seedu.duke.util.Messages;

public class DepositAction {
    private ArrayList<Deposit> deposits;
    private Ui ui;

    public DepositAction(ArrayList<Deposit> deposits, Ui ui) {
        this.deposits = deposits;
        this.ui = ui;
    }

    public ArrayList<Deposit> getDeposits() {
        return this.deposits;
    }

    public void addDeposit(String depositName, double depositAmount) {
        Deposit deposit = new Deposit(depositName, depositAmount);
        deposits.add(deposit);
    }

    public void deleteDeposit(int depositNo) throws GlobalInvalidNumberException {
        int num = validateDeposit(depositNo - 1);
        Deposit deletedDeposit = deposits.remove(num);
    }

    public void printDeposits() {
        ArrayList<String> messageLines = new ArrayList<String>();
        messageLines.add(Messages.DEPOSIT_PRINT.toString());

        int counter = 0;
        for (Deposit deposit : deposits) {
            String name = deposit.getDepositeName();
            Double amount = deposit.getDepositAmount();
            messageLines.add(String.format(Messages.DEPOSIT_PRINT_LINE.toString(), counter + 1, name, amount));
            counter++;
        }

        ui.printMessage(messageLines.toArray(new String[0]));
    }

    private int validateDeposit(int depositNo) throws GlobalInvalidNumberException {
        if (depositNo >= 0 && depositNo < deposits.size()) {
            return depositNo;
        } else {
            throw new GlobalInvalidNumberException();
        }
    }
}
