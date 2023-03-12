package seedu.duke.model;

public class Deposit {
    String name;
    double amount;

    public Deposit(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getDepositeName() {
        return this.name;
    }

    public double getDepositAmount() {
        return this.amount;
    }
}
