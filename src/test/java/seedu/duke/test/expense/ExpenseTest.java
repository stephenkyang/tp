package seedu.duke.test.expense;

import org.junit.jupiter.api.Test;
import seedu.duke.Ui;
import seedu.duke.action.BudgetAction;
import seedu.duke.action.ExpenseAction;
import seedu.duke.exception.BBException;
import seedu.duke.exception.GlobalInvalidNumberException;
import seedu.duke.model.Budget;
import seedu.duke.model.Expense;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//@@author tzixi

public class ExpenseTest {
    ArrayList<Expense> expenseList = new ArrayList<Expense>();
    ArrayList<Budget> budgetList = new ArrayList<Budget>();
    Ui ui = new Ui();
    ExpenseAction expenseAction = new ExpenseAction(expenseList, ui);
    BudgetAction budgetAction = new BudgetAction(budgetList, ui);

    @Test
    void addAndDeleteExpense() {
        String budgetName = "food";
        Double budgetLimit = 10.0;
        budgetAction.addBudget(budgetName, budgetLimit);

        String expenseName = "quesadilla";
        String expenseCategory = "food";
        double expenseAmount = 6.00;
        LocalDate expenseDate = LocalDate.now();

        try {
            expenseAction.addExpense(expenseCategory, expenseName, expenseAmount, expenseDate, budgetList);
        } catch (BBException e) {
            fail();
        }
        
        assert expenseList.size() != 0 : "add failed";
        assertEquals(1, expenseList.size());
        try {
            expenseAction.deleteExpense(1);
        } catch (GlobalInvalidNumberException e) {
            fail();
        }
        assert expenseList.size() != 1 : "delete failed";
        assertEquals(0, expenseList.size());
    }

    //@@author pinyoko573
    @Test
    void printExpensesTest() {
        budgetAction.addBudget("transport", 1000.00);
        budgetAction.addBudget("food", 500.00);
        try {
            expenseAction.addExpense("transport", "mrt e", 2.00, LocalDate.now().minusDays(1), budgetList);
            expenseAction.addExpense("transport", "mrt c", 2.00, LocalDate.now().minusDays(3), budgetList);
            expenseAction.addExpense("transport", "mrt d", 2.00, LocalDate.now().minusDays(2), budgetList);
            expenseAction.addExpense("transport", "mrt a", 2.00, LocalDate.now().minusDays(5), budgetList);
            expenseAction.addExpense("transport", "mrt b", 2.00, LocalDate.now().minusDays(4), budgetList);

            expenseAction.addExpense("food", "kfc", 20.00, LocalDate.now(), budgetList);
            expenseAction.addExpense("food", "macdonalds", 10.00, LocalDate.now(), budgetList);

            expenseAction.listExpenses(null);
            expenseAction.listExpenses("transport");
            expenseAction.listExpensesRange(LocalDate.now(), LocalDate.now(), "food");

            // Find expense
            expenseAction.findExpenses("mrt");
            
            // Filter expense by category
            ArrayList<Expense> filterCategory = ExpenseAction.filterExpensesByCategory(expenseList, "transport");
            assertEquals(5, filterCategory.size());

            // Filter expense by date
            ArrayList<Expense> filterDate = ExpenseAction.filterExpensesByDate(expenseList,
                LocalDate.now(), LocalDate.now());
            assertEquals(2, filterDate.size());

            // Sort expense by date
            ArrayList<Expense> sortDate = ExpenseAction.sortExpensesByDate(expenseList);
            assertEquals("mrt a", sortDate.get(0).getName());

        } catch (BBException e) {
            fail();
        }
    }
}
