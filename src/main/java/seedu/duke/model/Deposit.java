package seedu.duke.model;

import java.time.LocalDate;

public class Deposit extends Item {
    private LocalDate date;
    
    public Deposit(String name, double amount, LocalDate date) {
        super(name, amount);
        this.date = date;
    }
}
