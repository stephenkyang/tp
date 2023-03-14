package seedu.duke.command;

import jdk.jfr.Category;
import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.exception.BBException;
import seedu.duke.model.Budget;
import seedu.duke.model.Deposit;
import seedu.duke.model.Expense;
import seedu.duke.util.Pair;
import java.util.Hashtable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

public class StatsCommand extends Command {

    private static final String[] ACTIONS = {};
    private static final Pair[][] ACTIONS_REQUIRED_PARAMS = {};
    private static final Pair[][] ACTIONS_OPTIONAL_PARAMS = {};

    public StatsCommand() {
        super(CommandEnum.STATS, ACTIONS, ACTIONS_REQUIRED_PARAMS, ACTIONS_OPTIONAL_PARAMS);
    }


    public static void printStats(ArrayList<Expense> expenses, ArrayList<Budget> budgets, ArrayList<Deposit> deposits){
        Hashtable <String, Double> expenseCategoryTotal = new Hashtable<>();
        Hashtable <String, Double> budgetCategoryTotal = new Hashtable<>();
        double totalDeposits = 0.00;

        for (Expense e : expenses){
            if (e != null){
                String category = e.getCategory();
                Double expenseValue = e.getAmount();
                expenseCategoryTotal.put(category, expenseCategoryTotal.get(category) + expenseValue); // updates total expenses based on category
            }

        }

        for (Budget b : budgets){
            if (b != null){
                String category = b.getName();
                Double budgetMaxValue = b.getLimit();
                budgetCategoryTotal.put(category, budgetMaxValue);   // only done as once as budget max value is only stated once

            }
        }

        for (Deposit d : deposits){
            if (d != null){
                totalDeposits += d.getAmount();
            }
        }

        Set<String> categoriesBudget = budgetCategoryTotal.keySet();
        int index = 1;
        double totalBudgets = 0.00;
        double totalExpenses = 0.00;
        for (String category : categoriesBudget){
            System.out.print(index + ". ");
            System.out.print(category);

            Double budgetForCategory = budgetCategoryTotal.get(category);
            Double expenseForCategory = expenseCategoryTotal.get(category);
            totalBudgets += budgetForCategory;
            totalExpenses += expenseForCategory;
            int numberOfCrosses = (int) Math.round((expenseForCategory/budgetForCategory) * 10);
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
        System.out.println("Total budget progress:$ " + totalExpenses + "/" + totalBudgets + "(+" + totalDeposits + ")");

        if(totalExpenses < totalBudgets){
            System.out.println("Good job! You are on the right track!");
        }

        else{
            System.out.println("Oh no! You seem to be spending above your budget!");
        }


    }


    @Override
    public void execute(Data data, Ui ui) throws BBException {

    }



    @Override
    public boolean isExit() {
        return false;
    }
}
