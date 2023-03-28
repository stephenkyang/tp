package seedu.duke.action;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;

import seedu.duke.Ui;
import seedu.duke.exception.GlobalDateFromAfterToException;
import seedu.duke.exception.GlobalDateAfterTodayException;
import seedu.duke.exception.GlobalInvalidNumberException;
import seedu.duke.model.Deposit;
import seedu.duke.util.Commons;

// @@author stephenkyang
public class DepositAction {
    private static Comparator<Deposit> comparator = (deposit1, deposit2) -> deposit1.getDate()
            .compareTo(deposit2.getDate());
    
    private ArrayList<Deposit> deposits;
    private DepositUIResponse depositUi;

    public DepositAction(ArrayList<Deposit> deposits, Ui ui) {
        this.deposits = deposits;
        depositUi = new DepositUIResponse(ui);
    }

    public void addDeposit(String depositName, double depositAmount,
        LocalDate depositDate) throws GlobalDateAfterTodayException {
        
        // Checks if date is before today
        if (depositDate.isAfter(LocalDate.now())) {
            throw new GlobalDateAfterTodayException();
        }

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

        depositUi.printDepositDelSuccessful(deletedDeposit);
    }

    public void listDeposits() {
        // Sort the dates first
        ArrayList<Deposit> sortedDeposits = sortDepositsByDate(deposits);

        // Get previous months deposits
        // Gets the previous month of today, then shift the day to last day of month
        LocalDate previousMonthDate = LocalDate.now().minusMonths(1);
        previousMonthDate = previousMonthDate.with(TemporalAdjusters.lastDayOfMonth());

        ArrayList<Deposit> previousDeposits = filterDepositsByDate(sortedDeposits, LocalDate.MIN, previousMonthDate);

        // Get this month's deposit by slicing at size of previousDeposits
        ArrayList<Deposit> currentDeposits;
        if (previousDeposits.size() > 0) {
            currentDeposits = new ArrayList<Deposit>(sortedDeposits
                .subList(previousDeposits.size(), sortedDeposits.size()));
        } else {
            currentDeposits = sortedDeposits;
        }
        
        depositUi.printListDeposits(previousDeposits, currentDeposits);
    }

    public void findDeposit(String keyword) {
        ArrayList<Deposit> depositsWithKeyword = new ArrayList<>();
        for (Deposit deposit : this.deposits) {
            if (deposit.getName().contains(keyword)) {
                depositsWithKeyword.add(deposit);
            }
        }
        depositUi.printFindDeposits(depositsWithKeyword);
    }

    //@@author pinyoko573
    /**
     * Filter and sort the deposits by date.
     * 
     * @param from (optional) starting date of the deposits for filtering
     * @param to (optional) ending date of the deposits for filtering
     * @throws GlobalDateFromAfterToException when from date is after to date
     */
    public void listDepositsRange(LocalDate from, LocalDate to) throws GlobalDateFromAfterToException {
        if (from == null) {
            from = LocalDate.MIN;
        } else if (to == null) {
            to = LocalDate.MAX;
        }

        // Check if from < to
        if (from.isAfter(to)) {
            throw new GlobalDateFromAfterToException();
        }

        ArrayList<Deposit> filteredDeposits = filterDepositsByDate(deposits, from, to);
        ArrayList<Deposit> sortedDeposits = sortDepositsByDate(filteredDeposits);

        depositUi.printListDepositsRange(sortedDeposits, from, to);
    }

    // @@author stephenkyang
    /**
     * List deposits that contains the keyword specified by user 
     * @param name keyword that the user is finding
     */
    public void findDeposits(String name) {
        ArrayList<Deposit> filteredDeposits = new ArrayList<Deposit>();
        for (Deposit d : deposits) {
            if (d.getName().contains(name)) {
                filteredDeposits.add(d);
            }
        }

        depositUi.printFindDeposits(filteredDeposits);
    }

    // @@author stephenkyang
    public void clearDeposits(LocalDate from, LocalDate to) throws GlobalDateFromAfterToException {
        if (from == null) {
            from = LocalDate.MIN;
        }

        if (to == null) {
            to = LocalDate.MAX;
        }

        Commons.checkFromDateIsAfterTo(from, to);

        ArrayList<Deposit> depositList = filterDepositsByDate(deposits, from, to);
        deleteDeposits(depositList);

        depositUi.printClearDeposits(depositList);
    }

    // @@author stephenkyang
    /**
     * Prints user instructions on how to use deposit commands
     */
    public void depositHelp() {
        depositUi.printDepositCommands();
    }

    //@@author pinyoko573
    /**
     * Checks if a certain deposit already exists using id.
     * Used for deletion.
     * 
     * @param depositId id of the deposit
     * @return the deposit found in the nth element in list
     * @throws GlobalInvalidNumberException when deposit is not found
     */
    private int validateDeposit(int depositId) throws GlobalInvalidNumberException {        
        int elementNo = 0;
        for (Deposit d : deposits) {
            if (d.getId() == depositId) {
                return elementNo;
            }
            elementNo++;
        }

        throw new GlobalInvalidNumberException();
    }

    // @@author pinyoko573
    /**
     * Delete multiple deposits through the list of deposits given
     * 
     * @param removingDeposits deposits that will be deleted from the list
     */
    private void deleteDeposits(ArrayList<Deposit> removingDeposits) {
        for (Deposit d : removingDeposits) {
            deposits.remove(d);
        }
    }

    // @@author pinyoko573
    /**
     * Sorts the deposits given by date and returns back.
     * 
     * @param deposits list of deposits
     * @return deposits that are sorted by date
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<Deposit> sortDepositsByDate(ArrayList<Deposit> deposits) {
        ArrayList<Deposit> sortedDeposits = (ArrayList<Deposit>) deposits.clone();
        sortedDeposits.sort(comparator);
        return sortedDeposits;
    }

    // @@author pinyoko573
    /**
     * Filter the deposits given by date range and returns back.
     * 
     * @param deposits list of deposits
     * @param from starting date
     * @param to ending date
     * @return list of deposits that are within the date range
     */
    public static ArrayList<Deposit> filterDepositsByDate(ArrayList<Deposit> deposits, LocalDate from, LocalDate to) {
        ArrayList<Deposit> filteredDeposits = new ArrayList<Deposit>();
        
        for (Deposit d : deposits) {
            LocalDate date = d.getDate();
            if ((date.isBefore(to) || date.isEqual(to)) && date.isAfter(from) || date.isEqual(from)) {
                filteredDeposits.add(d);
            }
        }

        return filteredDeposits;
    }

    // @@author pinyoko573
    /**
     * Get the total amount of deposit from given list of deposits.
     * 
     * @param deposits list of deposits
     * @return total amount of deposits
     */
    public static double getTotalDeposits(ArrayList<Deposit> deposits) {
        double total = 0;
        
        for (Deposit d : deposits) {
            total += d.getAmount();
        }

        return total;
    }
}
