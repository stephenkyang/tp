package seedu.duke.model;

public class Budget extends Item {
    /**
     * Creates a template for each budget, which includes the name, its completion status and budget type
     *
     * @param name name of budget
     * @param limit the monetary value limit of the budget
     */
    public Budget(String name, double amount) {
        super(name, amount);
    }
}
