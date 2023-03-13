package seedu.duke.model;

public class Expense {
    private String category;
    private String name;
    private double amount;
    private String date;

    public Expense(String category, String name, double amount, String date){
        this.category = category;
        this.name = name;
        this.amount = amount;
        this.date = date;
    }

    public String getCategory() {
        return this.category;
    }

    public String getName() {
        return this.name;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        //format is name, amount, /category
        return " " + name + " " + amount + " /" + category;
    }
}
