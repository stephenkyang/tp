package seedu.duke.model;

public abstract class Item {
    private String name;
    private double amount;

    public Item(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    /**
     * Gets the name of the Item.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the amount of the Item.
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Sets the name of an Item.
     *
     * @param name name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the amount of an Item.
     * For budget, it sets the limit.
     *
     * @param amount amount of the item
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
