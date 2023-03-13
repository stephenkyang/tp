package seedu.duke.action;

import java.util.ArrayList;

import seedu.duke.Ui;
import seedu.duke.exception.GlobalInvalidNumberException;
import seedu.duke.model.Expense;

public class ExpenseAction {
    private ArrayList<Expense> expenses;
    private ExpenseUIResponse expenseUi;

    public ExpenseAction(ArrayList<Expense> expenses, Ui ui) {
        this.expenses = expenses;
        expenseUi = new ExpenseUIResponse(ui);
    }

    public void addExpense(String expenseCategory, String expenseName, Double expenseAmount, String expenseDate) {
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
