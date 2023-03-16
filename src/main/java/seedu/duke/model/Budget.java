package seedu.duke.model;

public class Budget extends Item {
    /**
     * Creates a template for each budget, which includes the name and budget type
     *
     * @param name name of budget
     * @param amount the monetary value limit of the budget
     */
    //@@author chongyongrui
    public Budget(String name, double amount) {
        super(name, amount);
    }
}
