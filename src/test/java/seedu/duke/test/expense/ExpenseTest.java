package seedu.duke.test.expense;

import org.junit.jupiter.api.Test;
import seedu.duke.Ui;
import seedu.duke.action.BudgetAction;
import seedu.duke.action.ExpenseAction;
import seedu.duke.exception.BBException;
import seedu.duke.exception.GlobalDateFromAfterToException;
import seedu.duke.exception.GlobalInvalidNumberException;
import seedu.duke.model.Budget;
import seedu.duke.model.Expense;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    public void restoreStreams() {
        System.setOut(originalOut);
    }

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


        assert expenseList.size() != 0 :  "add failed";
        assertEquals(1, expenseList.size());
        try {
            expenseAction.deleteExpense(1);
        } catch (GlobalInvalidNumberException e) {
            fail();
        }
        assert expenseList.size() != 1 : "delete failed";
        assertEquals(0, expenseList.size());
    }

    @Test
    void findExpense() {
        String expenseCat = "Food";
        Double budgetLimit = 100.0;
        budgetAction.addBudget(expenseCat, budgetLimit);

        String[] expenseNames = {"apple", "pear", "orange", "pineapple"};
        double expenseAmount = 20.00;
        LocalDate[] expenseDate = {LocalDate.of(2002, 1,1),
                LocalDate.of(2023, 4,9),
                LocalDate.now(), LocalDate.now()};
        try {
            for (int i = 0; i < expenseNames.length ; i++) {
                expenseAction.addExpense(expenseCat, expenseNames[i], expenseAmount, expenseDate[i], budgetList);
            }
        } catch (BBException e) {
            System.out.println("fail");
        }

        assert expenseList.size() != 0 : "add failed";
        assertEquals(4, expenseList.size());
        try {
            expenseAction.clearExpenses(expenseDate[0], expenseDate[0], expenseCat);
        } catch (GlobalDateFromAfterToException e) {
            fail();
        }
        assert expenseList.size() != 4 : "clear failed";
        assertEquals(3, expenseList.size());

        setUpStreams();
        expenseAction.findExpenses("pear");
        String outContentInString = outContent.toString();
        String newline = System.lineSeparator();
        String expectedOutput = ("_______________" + newline +
                "Here are the expenses you searched:" + newline +
                "Expense No 2. [Food] pear ($20.00) on 09 Apr 2023" + newline +
                "_______________" + newline);
        for (int i = 0; i < expectedOutput.length(); i++) {
            if (expectedOutput.charAt(i) != outContentInString.charAt(i)) {
                System.out.println(i);
                assertEquals(i, -1);
                fail();
            }
        }
        restoreStreams();
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
