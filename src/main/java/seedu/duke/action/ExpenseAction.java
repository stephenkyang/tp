package seedu.duke.action;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import seedu.duke.Ui;
import seedu.duke.exception.GlobalInvalidNumberException;
import seedu.duke.model.Expense;

//@@author tzixi
public class ExpenseAction {
    public static ArrayList<Expense> expenses = new ArrayList<Expense>();
    private ExpenseUIResponse expenseUi;

    public ExpenseAction(ArrayList<Expense> expenses, Ui ui) {
        this.expenses = expenses;
        expenseUi = new ExpenseUIResponse(ui);
    }


    public static double findRelatedExpenses(String budgetName) {
        int i = 1;
        double totalExpenseValue = 0;
        for (Expense expense : expenses) {
            if (expense != null) {
                if (Objects.equals(expense.getCategory(), budgetName)) {
                    System.out.println(i + ". " + expense.getName() + " with amount of $" + expense.getAmount());
                    totalExpenseValue += expense.getAmount();
                }
            }

        }
        return totalExpenseValue;
    }


    public void addExpense(String expenseCategory, String expenseName, Double expenseAmount, LocalDate expenseDate) {

        Expense expense = new Expense(expenseCategory, expenseName, expenseAmount, expenseDate);
        expenses.add(expense);

        expenseUi.printExpenseAddSuccessful(expense, expenses.size());
    }

    public void deleteExpense(int expenseNo) throws GlobalInvalidNumberException {
        int num = validateExpense(expenseNo - 1);
        Expense deletedExpense = expenses.remove(num);

        expenseUi.printExpenseDelSuccessful(deletedExpense, num);
    }

    public void printExpenses() {
        expenseUi.printListExpenses(expenses);
    }

    private int validateExpense(int expenseNo) throws GlobalInvalidNumberException {
        if (expenseNo >= 0 && expenseNo < expenses.size()) {
            return expenseNo;
        } else {
            throw new GlobalInvalidNumberException();
        }
    }
}
