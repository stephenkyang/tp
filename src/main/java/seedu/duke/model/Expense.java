package seedu.duke.model;

import java.time.LocalDate;

//@@author tzixi
public class Expense extends Item {
    private String category;
    private LocalDate date;
    private int id;

    /**
     * Creates a template for each expense, which includes the category, name, amount and date expense was done.
     *
     * @param category category of expense
     * @param name name of expense
     * @param amount amount of expense
     * @param date date of expense
     * @param id id of expense
     */
    public Expense(String category, String name, double amount, LocalDate date, int id) {
        super(name, amount);
        this.category = category;
        this.date = date;
        this.id = id;
    }

    /**
     * Function to return category of expense
     *
     * @return category of expense
     */

    public String getCategory() {
        return this.category;
    }

    /**
     * Function to return date of expense
     *
     * @return date of expense
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Function to return id of expense
     *
     * @return id of expense
     */
    public int getId() {
        return this.id;
    }
}
