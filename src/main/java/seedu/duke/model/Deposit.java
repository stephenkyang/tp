package seedu.duke.model;

import java.time.LocalDate;

//@@author stephenkyang
public class Deposit extends Item {
    private LocalDate date;
    private int id;
    
    /**
     * Creates a template for each deposit, which includes the name, amount and date deposited.
     *
     * @param name name of the deposit
     * @param amount amount that is deposited
     * @param date the date that is deposited
     */
    public Deposit(String name, double amount, LocalDate date, int id) {
        super(name, amount);
        this.date = date;
        this.id = id;
    }

    /**
     * Returns the date of deposit.
     * 
     * @return date that is deposited
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns the id of deposit.
     * 
     * @return date that is deposited
     */
    public int getId() {
        return this.id;
    }
}
