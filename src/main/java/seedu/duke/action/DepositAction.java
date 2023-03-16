package seedu.duke.action;

import java.util.ArrayList;

import seedu.duke.Ui;
import seedu.duke.exception.GlobalInvalidNumberException;
import seedu.duke.model.Deposit;

public class DepositAction {
    private ArrayList<Deposit> deposits;
    private DepositUIResponse depositUi;

    public DepositAction(ArrayList<Deposit> deposits, Ui ui) {
        this.deposits = deposits;
        depositUi = new DepositUIResponse(ui);
    }

    public ArrayList<Deposit> getDeposits() {
        return this.deposits;
    }

    public void addDeposit(String depositName, double depositAmount) {
        Deposit deposit = new Deposit(depositName, depositAmount, null);
        deposits.add(deposit);

        depositUi.printDepositAddSuccessful(deposit, deposits.size());
    }

    public void deleteDeposit(int depositNo) throws GlobalInvalidNumberException {
        int num = validateDeposit(depositNo - 1);
        Deposit deletedDeposit = deposits.remove(num);
    }

    public void printDeposits() {
        depositUi.printListDeposits(deposits);
    }

    private int validateDeposit(int depositNo) throws GlobalInvalidNumberException {
        if (depositNo >= 0 && depositNo < deposits.size()) {
            return depositNo;
        } else {
            throw new GlobalInvalidNumberException();
        }
    }
}
