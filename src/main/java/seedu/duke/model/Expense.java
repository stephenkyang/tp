package seedu.duke.model;

import java.time.LocalDate;

//@@author tzixi
public class Expense extends Item {
    private String category;
    private LocalDate date;

    public Expense(String category, String name, double amount) {
        super(name, amount);
        this.category = category;
        this.date = LocalDate.now();
    }

    public Expense(String category, String name, double amount, LocalDate date){
        super(name, amount);
        this.category = category;
        this.date = date;
    }

    public String getCategory() {
        return this.category;
    }

    public LocalDate getDate() {
        return this.date;
    }
}
