package seedu.duke.model;

import java.time.LocalDate;

//@@author tzixi
public class Expense extends Item {
    private final String category;
    private final LocalDate date;
    private final int id;

    public Expense(String category, String name, double amount, LocalDate date, int id){
        super(name, amount);
        this.category = category;
        this.date = date;
        this.id = id;
    }

    public String getCategory() {
        return this.category;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public int getId() {
        return this.id;
    }
}
