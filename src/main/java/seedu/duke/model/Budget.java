package seedu.duke.model;

public class Budget {

    private String name;
    private double limit;

    /**
     * Creates a template for each budget, which includes the name, its completion status and budget type
     *
     * @param name name of budget
     * @param limit the monetary value limit of the budget
     */
    public Budget(String name, double limit) {
        this.name = name;
        this.limit = limit;
    }

    /**
     * Sets the limit of the budget
     *
     * @param limit limit of the budget
     */
    public void setLimit(Double limit) {
        this.limit = limit;
    }

    /**
     * Gets the name of the budget
     *
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the budget
     *
     * @param name Name of the budget
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of budget
     *
     * @return name of the budget
     */
    public double getLimit() {
        return this.limit;
    }
}
