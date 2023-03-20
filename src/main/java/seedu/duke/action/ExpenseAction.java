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

        // Get the id of last expense and increment it
        int expenseId;
        if (expenses.size() != 0) {
            Expense lastExpense = expenses.get(expenses.size() - 1);
            expenseId = lastExpense.getId() + 1;
        } else {
            expenseId = 1;
        }

        Expense expense = new Expense(expenseCategory, expenseName, expenseAmount, expenseDate, expenseId);
        expenses.add(expense);

        expenseUi.printExpenseAddSuccessful(expense, expenses.size());
    }

    public void deleteExpense(int expenseId) throws GlobalInvalidNumberException {
        int elementNo = validateExpense(expenseId);
        Expense deletedExpense = expenses.remove(elementNo);

        expenseUi.printExpenseDelSuccessful(deletedExpense, expenseId);
    }

    public void printExpenses() {
        expenseUi.printListExpenses(expenses);
    }

    //@@author pinyoko573
    private int validateExpense(int expenseId) throws GlobalInvalidNumberException {
        int elementNo = 0;
        for (Expense expense : expenses) {
            if (expense.getId() == expenseId) {
                return elementNo;
            }
            elementNo++;
        }

        throw new GlobalInvalidNumberException();
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
