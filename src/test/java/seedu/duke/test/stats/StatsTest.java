package seedu.duke.test.stats;

import seedu.duke.Data;
import seedu.duke.action.BudgetAction;
import seedu.duke.action.ExpenseAction;
import seedu.duke.action.StatsAction;
import seedu.duke.command.StatsCommand;
import seedu.duke.exception.BBException;
import seedu.duke.exception.StatsInvalidOptionsException;
import seedu.duke.model.Budget;
import seedu.duke.model.Deposit;
import seedu.duke.model.Expense;

import org.junit.jupiter.api.Test;
import seedu.duke.Ui;
import seedu.duke.action.DepositAction;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.fail;

//@@author SaiChaitanya13

public class StatsTest {
    ArrayList<Expense> expenseList = new ArrayList<>();
    ArrayList<Budget> budgetList = new ArrayList<>();
    ArrayList<Deposit> depositList = new ArrayList<>();
    Ui ui = new Ui();
    Data data = new Data();
    ExpenseAction expenseAction = new ExpenseAction(expenseList, ui);
    BudgetAction budgetAction = new BudgetAction(budgetList, ui);
    DepositAction depositAction = new DepositAction(depositList, ui);
    StatsAction statsAction = new StatsAction(data, ui);

    @Test
    void displayStats() throws BBException {
        // add an expense, budget and deposit

        String budgetName = "transport";
        double budgetLimit = 100.0;
        budgetAction.addBudget(budgetName,budgetLimit);

        String expenseName = "Grab to NUS";
        String expenseCategory = "transport";
        double expenseAmount = 20.0;
        LocalDate dateAdded = LocalDate.now();
        expenseAction.addExpense(expenseCategory, expenseName, expenseAmount, dateAdded, budgetList);

        String depositName = "Part time job";
        double depositAmount = 500.0;
        LocalDate depositDate = LocalDate.now();
        depositAction.addDeposit(depositName, depositAmount, depositDate);


        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();

        boolean showDeposit = true;
        boolean showExpense = true;

        try {
            statsAction.showStats(month, year, showDeposit, showExpense);

        } catch(StatsInvalidOptionsException e){
            fail();
        }
    }

    @Test
    public void execute_noError(){
        StatsCommand statsCommand = new StatsCommand();
        Data data = new Data();
        Ui ui = new Ui();
        statsCommand.setAction("show");
        statsCommand.setParams(null, new String[] {null, null, null});

        assertDoesNotThrow(
                () -> statsCommand.execute(data, ui)
        );

    }



}
