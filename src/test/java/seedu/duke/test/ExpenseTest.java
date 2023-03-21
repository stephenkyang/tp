package seedu.duke.test;

import org.junit.jupiter.api.Test;
import seedu.duke.Ui;
import seedu.duke.action.BudgetAction;
import seedu.duke.action.ExpenseAction;
import seedu.duke.exception.ExpenseBudgetNotFoundException;
import seedu.duke.exception.GlobalInvalidNumberException;
import seedu.duke.model.Budget;
import seedu.duke.model.Expense;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ExpenseTest {
    ArrayList<Expense> expenseList = new ArrayList<Expense>();
    ArrayList<Budget> budgetList = new ArrayList<Budget>();
    Ui ui = new Ui();
    ExpenseAction expenseAction = new ExpenseAction(expenseList, ui);
    BudgetAction budgetAction = new BudgetAction(budgetList, ui);

    @Test
    void addAndDeleteExpense() throws ExpenseBudgetNotFoundException {
        String budgetName = "food";
        Double budgetLimit = 10.0;
        budgetAction.addBudget(budgetName, budgetLimit);
        String expenseName = "quesadilla";
        String expenseCategory = "food";
        double expenseAmount = 6.00;
        LocalDate depositDate = LocalDate.now();
        expenseAction.addExpense(expenseName, expenseCategory, expenseAmount, depositDate, budgetList);
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
}
