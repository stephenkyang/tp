package seedu.duke.model;

import java.time.LocalDate;

//@@author stephenkyang
public class Deposit extends Item {
    private LocalDate date;
    
    /**
     * Creates a template for each deposit, which includes the name, amount and date deposited.
     * If date is not specified, it will take as today's date
     * 
     * @param name name of budget
     * @param amount the monetary value limit of the budget
     */
    public Deposit(String name, double amount) {
        super(name, amount);
        this.date = LocalDate.now();
    }
    
    /**
     * Creates a template for each deposit, which includes the name, amount and date deposited.
     *
     * @param name name of the deposit
     * @param amount amount that is deposited
     * @param date the date that is deposited
     */
    public Deposit(String name, double amount, LocalDate date) {
        super(name, amount);
        this.date = date;
    }

    /**
     * Returns the date of deposit.
     * 
     * @return date that is deposited
     */
    public LocalDate getDate() {
        return this.date;
    }
}
