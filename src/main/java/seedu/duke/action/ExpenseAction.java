package seedu.duke.action;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import seedu.duke.Ui;
import seedu.duke.exception.ExpenseBudgetNotFoundException;
import seedu.duke.exception.GlobalInvalidNumberException;
import seedu.duke.model.Budget;
import seedu.duke.model.Expense;

//@@author tzixi
public class ExpenseAction {
    public ArrayList<Expense> expenses;
    private ExpenseUIResponse expenseUi;

    public ExpenseAction(ArrayList<Expense> expenses, Ui ui) {
        this.expenses = expenses;
        expenseUi = new ExpenseUIResponse(ui);
    }

    public double findRelatedExpenses(String budgetName) {
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

    public void addExpense(String expenseCategory, String expenseName, Double expenseAmount,
        LocalDate expenseDate, ArrayList<Budget> budgets) throws ExpenseBudgetNotFoundException {
        // Check if the expense category exists
        boolean isExist = BudgetAction.validateBudget(expenseName, budgets);
        if (!isExist) {
            throw new ExpenseBudgetNotFoundException();
        }

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

    //@@author pinyoko573
    protected static void clearExpenses(String budgetName, ArrayList<Expense> expenseList) {
        for (Expense expense : expenseList) {
            if (expense.getCategory().equals(budgetName)) {
                expenseList.remove(expense);
            }
        }
    }
}
