package seedu.duke.action;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;

import seedu.duke.Ui;
import seedu.duke.exception.BBException;
import seedu.duke.exception.ExpenseBudgetNotFoundException;
import seedu.duke.exception.GlobalDateFromAfterToException;
import seedu.duke.exception.GlobalDateAfterTodayException;
import seedu.duke.exception.GlobalInvalidNumberException;
import seedu.duke.model.Budget;
import seedu.duke.model.Expense;
import seedu.duke.util.Commons;

//@@author tzixi
public class ExpenseAction {
    private static Comparator<Expense> comparator = (expense1, expense2) -> expense1.getDate()
            .compareTo(expense2.getDate());
    public ArrayList<Expense> expenses;
    private ExpenseUIResponse expenseUi;

    public ExpenseAction(ArrayList<Expense> expenses, Ui ui) {
        this.expenses = expenses;
        expenseUi = new ExpenseUIResponse(ui);
    }

    public void addExpense(String expenseCategory, String expenseName, Double expenseAmount,
        LocalDate expenseDate, ArrayList<Budget> budgets) throws BBException {
        // Check if the expense category exists
        boolean isExist = BudgetAction.validateBudget(expenseCategory, budgets);
        if (!isExist) {
            throw new ExpenseBudgetNotFoundException();
        }

        // Checks if date is before today
        if (expenseDate.isAfter(LocalDate.now())) {
            throw new GlobalDateAfterTodayException();
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

        expenseUi.printExpenseAddSuccessful(expense);
    }

    public void deleteExpense(int expenseId) throws GlobalInvalidNumberException {
        int elementNo = validateExpense(expenseId);
        Expense deletedExpense = expenses.remove(elementNo);

        expenseUi.printExpenseDelSuccessful(deletedExpense);
    }

    @SuppressWarnings("unchecked")
    public void listExpenses(String category) {
        ArrayList<Expense> expenseList = (ArrayList<Expense>) expenses.clone();
        // Filter the expenses by category, if any
        if (category != null) {
            expenseList = filterExpensesByCategory(expenseList, category);
        }

        // Sort the dates first
        ArrayList<Expense> sortedExpenses = sortExpensesByDate(expenseList);

        // Get previous months expenses
        // Gets the previous month of today, then shift the day to last day of month
        LocalDate previousMonthDate = LocalDate.now().minusMonths(1);
        previousMonthDate = previousMonthDate.with(TemporalAdjusters.lastDayOfMonth());

        ArrayList<Expense> previousExpenses = filterExpensesByDate(sortedExpenses, LocalDate.MIN, previousMonthDate);

        // Get this month's expense by slicing at size of previousExpenses
        ArrayList<Expense> currentExpenses;
        if (previousExpenses.size() > 0) {
            currentExpenses = new ArrayList<Expense>(sortedExpenses
                .subList(previousExpenses.size(), sortedExpenses.size()));
        } else {
            currentExpenses = sortedExpenses;
        }
        
        expenseUi.printListExpenses(previousExpenses, currentExpenses, category);
    }

    //@@author pinyoko573
    @SuppressWarnings("unchecked")
    public void listExpensesRange(LocalDate from, LocalDate to, String category) throws GlobalDateFromAfterToException {
        if (from == null) {
            from = LocalDate.MIN;
        } else if (to == null) {
            to = LocalDate.MAX;
        }

        Commons.checkFromDateIsAfterTo(from, to);

        ArrayList<Expense> expenseList = (ArrayList<Expense>) expenses.clone();
        // Filter the expenses by category, if any
        if (category != null) {
            expenseList = filterExpensesByCategory(expenseList, category);
        }

        ArrayList<Expense> filteredExpenses = filterExpensesByDate(expenseList, from, to);
        ArrayList<Expense> sortedExpenses = sortExpensesByDate(filteredExpenses);

        expenseUi.printListExpensesRange(sortedExpenses, from, to, category);
    }

    // @@author pinyoko573
    public void findExpenses(String name) {
        ArrayList<Expense> filteredExpenses = new ArrayList<Expense>();
        for (Expense e : expenses) {
            if (e.getName().contains(name)) {
                filteredExpenses.add(e);
            }
        }

        expenseUi.printFindExpenses(filteredExpenses);
    }

    // @@author pinyoko573
    @SuppressWarnings("unchecked")
    public void clearExpenses(LocalDate from, LocalDate to, String category) throws GlobalDateFromAfterToException {
        if (from == null) {
            from = LocalDate.MIN;
        }
        
        if (to == null) {
            to = LocalDate.MAX;
        }

        Commons.checkFromDateIsAfterTo(from, to);

        ArrayList<Expense> expenseList = (ArrayList<Expense>) expenses.clone();
        // Filter the expenses by category, if any
        if (category != null) {
            expenseList = filterExpensesByCategory(expenseList, category);
        }

        expenseList = filterExpensesByDate(expenseList, from, to);
        deleteExpenses(expenseList);

        expenseUi.printClearExpenses(expenseList);
    }

    // @@author pinyoko573
    public void expenseHelp() {
        expenseUi.printExpenseCommands();
    }

    // @@author pinyoko573
    private int validateExpense(int expenseId) throws GlobalInvalidNumberException {
        int elementNo = 0;
        for (Expense e : expenses) {
            if (e.getId() == expenseId) {
                return elementNo;
            }
            elementNo++;
        }

        throw new GlobalInvalidNumberException();
    }

    // @@author pinyoko573
    private void deleteExpenses(ArrayList<Expense> removingExpenses) {
        for (Expense e : removingExpenses) {
            expenses.remove(e);
        }
    }

    // @@author pinyoko573
    public static void clearExpensesByCategory(String budgetName, ArrayList<Expense> expenses) {
        for (Expense e : expenses) {
            if (e.getCategory().equals(budgetName)) {
                expenses.remove(e);
            }
        }
    }

    // @@author pinyoko573
    @SuppressWarnings("unchecked")
    public static ArrayList<Expense> sortExpensesByDate(ArrayList<Expense> expenses) {
        ArrayList<Expense> sortedExpenses = (ArrayList<Expense>) expenses.clone();
        sortedExpenses.sort(comparator);
        return sortedExpenses;
    }

    // @@author pinyoko573
    public static ArrayList<Expense> filterExpensesByDate(ArrayList<Expense> expenses, LocalDate from, LocalDate to) {
        ArrayList<Expense> filteredExpenses = new ArrayList<Expense>();
        
        for (Expense e : expenses) {
            LocalDate date = e.getDate();
            if ((date.isBefore(to) || date.isEqual(to)) && date.isAfter(from) || date.isEqual(from)) {
                filteredExpenses.add(e);
            }
        }

        return filteredExpenses;
    }

    // @@author pinyoko573
    public static ArrayList<Expense> filterExpensesByCategory(ArrayList<Expense> expenses, String category) {
        ArrayList<Expense> filteredExpenses = new ArrayList<Expense>();

        for (Expense e : expenses) {
            if (e.getCategory().equals(category)) {
                filteredExpenses.add(e);
            }
        }

        return filteredExpenses;
    }

    // @@author pinyoko573
    public static double getTotalExpenses(ArrayList<Expense> expenses) {
        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }

        return total;
    }

    // public double findRelatedExpenses(String budgetName) {
    //     int i = 1;
    //     double totalExpenseValue = 0;
    //     for (Expense expense : expenses) {
    //         if (expense != null) {
    //             if (Objects.equals(expense.getCategory(), budgetName)) {
    //                 System.out.println(i + ". " + expense.getName() + " with amount of $" + expense.getAmount());
    //                 totalExpenseValue += expense.getAmount();
    //             }
    //         }

    //     }
    //     return totalExpenseValue;
    // }
}
