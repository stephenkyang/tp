package seedu.duke.action;

import java.util.ArrayList;

import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.model.Budget;
import seedu.duke.model.Deposit;
import seedu.duke.model.Expense;

//@@author SaiChaitanya13
public class StatsAction {
    private Data data;
    private StatsUIResponse statsUi;

    public StatsAction(Data data, Ui ui) {
        this.data = data;
        statsUi = new StatsUIResponse(ui);
    }

    public void viewStats() {
        ArrayList<Budget> budgets = data.getBudgets();
        ArrayList<Deposit> deposits = data.getDeposits();

        double totalDeposits = 0.00;

        for (Deposit d : deposits){
            if (d != null){
                totalDeposits += d.getAmount();
            }
        }

        int index = 1;
        double totalBudgets = 0.00;
        double totalExpenses = 0.00;
        for (Budget b : budgets){
            System.out.print(index + ". ");
            System.out.print(b.getName());

            Double expenseForCategory = getExpenses(b.getName());

            totalBudgets += b.getAmount();
            totalExpenses += expenseForCategory;
            int numberOfCrosses = (int) Math.round((expenseForCategory/b.getAmount()) * 10);
            int numberOfDashes = 0;
            if (numberOfCrosses < 10){
                numberOfDashes = 10 - numberOfCrosses;
            }

            for (int i = 0; i < numberOfCrosses; i++){
                System.out.print("X");
            }

            for (int j = 0; j < numberOfDashes; j++){
                System.out.print("-");
            }

            System.out.println(expenseForCategory + "/" + expenseForCategory);
        }

        System.out.println("Extra deposits: $" + totalDeposits);
        System.out.println("Total budget progress: $" + totalExpenses + "/"
            + totalBudgets + "(+" + totalDeposits + ")");

        if (totalExpenses <= (totalBudgets + totalDeposits)) {
            System.out.println("Good job! You are on the right track!");
        } else {
            System.out.println("Oh no! You seem to be spending above your budget!");
        }
    }

    private double getExpenses(String categoryName) {
        double totalExpenses = 0;

        for (Expense e : data.getExpenses()) {
            if (e.getCategory().equals(categoryName)) {
                totalExpenses += e.getAmount();
            }
        }

        return totalExpenses;
    }
}
