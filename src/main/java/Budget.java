public class Budget {
    protected String budgetName;
    protected double budgetLimit;


    /**
     * Creates a template for each budget, which includes the name, its completion status and budget type
     *
     * @param name name of budget
     * @param budgetLimit the monetary value limit of the budget
     */

    public Budget(String name, double budgetLimit) {
        setBudgetName(name);
    }

    /**
     * Sets the name of the budget
     *
     * @param name Name of the budget
     */

    public void setBudgetName(String name) {
        this.budgetName = name;
    }


    /**
     * Returns the type of budget
     *
     * @param budget budget to find the type for
     * @return type of the budget
     */
    public String getBudgetName(Budget budget) {
        return this.budgetName;
    }

    /**
     * Returns the name of budget
     *
     * @return name of the budget
     */
    public String getBudgetName() {
        return budgetName;
    }


    /**
     * prints all information about the budget
     */

    public void printBudget() {

        System.out.println("Budget name: " + this.budgetName + "with limit of $" + this.budgetLimit);
    }


}
