package seedu.duke.action;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;

import seedu.duke.Ui;
import seedu.duke.exception.GlobalInvalidNumberException;
import seedu.duke.model.Deposit;

public class DepositAction {
    private static Comparator<Deposit> comparator = (deposit1, deposit2) -> deposit1.getDate()
            .compareTo(deposit2.getDate());
            
    private ArrayList<Deposit> deposits;
    private DepositUIResponse depositUi;

    public DepositAction(ArrayList<Deposit> deposits, Ui ui) {
        this.deposits = deposits;
        depositUi = new DepositUIResponse(ui);
    }

    public void addDeposit(String depositName, double depositAmount, LocalDate depositDate) {
        // Get the id of last deposit and increment it
        int depositId;
        if (deposits.size() != 0) {
            Deposit lastDeposit = deposits.get(deposits.size() - 1);
            depositId = lastDeposit.getId() + 1;
        } else {
            depositId = 1;
        }

        Deposit deposit = new Deposit(depositName, depositAmount, depositDate, depositId);
        deposits.add(deposit);

        depositUi.printDepositAddSuccessful(deposit);
    }

    public void deleteDeposit(int depositId) throws GlobalInvalidNumberException {
        assert deposits.size() > 0 : "No deposits to delete!";
        int elementNo = validateDeposit(depositId);
        Deposit deletedDeposit = deposits.remove(elementNo);

        // assert deposits.size() > 0 : "No deposits to delete!";
        // int num = validateDeposit(depositNo - 1);
        // Deposit deletedDeposit = deposits.remove(num);
        // depositUi.printDepositDeleteSuccessful(deletedDeposit, deposits.size());

        depositUi.printDepositDelSuccessful(deletedDeposit);
    }

    public void clearDeposits(LocalDate startDate, LocalDate endDate) {
        int previousSize = this.deposits.size();
        this.deposits.removeIf(deposit -> startDate.isBefore(deposit.getDate()) && endDate.isAfter(deposit.getDate()));
        this.deposits.removeIf(deposit -> startDate.isEqual(deposit.getDate()) || endDate.isEqual(deposit.getDate()));
        int currentSize = this.deposits.size();
        depositUi.printDepositClearSuccessful(previousSize - currentSize);
    }
    public void depositHelp() {
        depositUi.printDepositCommands();
    }

    public void listDeposits() {
        // Sort the dates first
        ArrayList<Deposit> sortedDeposits = getSortedDeposits(deposits);

        // Get previous months deposits
        ArrayList<Deposit> previousDeposits = getPreviousDeposits(sortedDeposits);

        // Get this month's deposit by slicing previousDeposits size
        ArrayList<Deposit> currentDeposits;
        if (previousDeposits.size() > 0) {
            currentDeposits = new ArrayList<Deposit>(sortedDeposits
                .subList(previousDeposits.size(), sortedDeposits.size()));
        } else {
            currentDeposits = sortedDeposits;
        }
        
        depositUi.printListDeposits(previousDeposits, currentDeposits);
    }

    public void printDepositsRange(LocalDate from, LocalDate to) {
        
    }

    //@@author pinyoko573
    private int validateDeposit(int depositId) throws GlobalInvalidNumberException {        
        int elementNo = 0;
        for (Deposit deposit : deposits) {
            if (deposit.getId() == depositId) {
                return elementNo;
            }
            elementNo++;
        }

        throw new GlobalInvalidNumberException();
    }

    @SuppressWarnings("unchecked")
    private static ArrayList<Deposit> getSortedDeposits(ArrayList<Deposit> deposits) {
        ArrayList<Deposit> sortedDeposits = (ArrayList<Deposit>) deposits.clone();
        sortedDeposits.sort(comparator);
        return sortedDeposits;
    }

    //@@author pinyoko573
    private ArrayList<Deposit> getPreviousDeposits(ArrayList<Deposit> sortedDeposits) {
        ArrayList<Deposit> previousDeposits = new ArrayList<Deposit>();

        // Gets the previous month of today, then shift the day to last day of month
        LocalDate previousMonthDate = LocalDate.now().minusMonths(1);
        previousMonthDate = previousMonthDate.with(TemporalAdjusters.lastDayOfMonth());

        for (int i = 0; i < sortedDeposits.size(); i++) {
            Deposit d = sortedDeposits.get(i);
            if (d.getDate().isAfter(previousMonthDate)) {
                previousDeposits = new ArrayList<Deposit>(sortedDeposits.subList(0, i));
                break;
            }
        }

        return previousDeposits;
    }
}
