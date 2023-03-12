package seedu.duke.model;

public class Deposit {
    private String name;
    private double amount;

    public Deposit(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return this.name;
    }

    public double getAmount() {
        return this.amount;
    }
}
