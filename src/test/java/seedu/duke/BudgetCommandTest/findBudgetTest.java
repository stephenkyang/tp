package seedu.duke.BudgetCommandTest;

import org.junit.jupiter.api.Test;
import seedu.duke.Ui;
import seedu.duke.action.BudgetAction;
import seedu.duke.model.Budget;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class findBudgetTest {

    ArrayList<Budget> budgetList = new ArrayList<Budget>();


    Ui ui = new Ui();
    BudgetAction budgetAction = new BudgetAction(budgetList, ui);


    @Test
    void findBudget_existingUniqueBudget_success() {
        String budgetName = "transport";
        Double budgetLimit = 10.0;
        budgetAction.addBudget(budgetName, budgetLimit);
        assertEquals(budgetList.size(), 1);
        assert budgetList.size() == 1 : "budget creation error";
        PrintStream originalStream;
        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
        PrintStream newStream = new PrintStream(testOutput);
        originalStream = System.out;
        System.setOut(newStream);
        budgetAction.findBudget("transport");
        System.setOut(originalStream);
        assertEquals(testOutput.toString(), "_______________\n" +
                "1. Name: \"transport\" Limit: $10.00\n" +
                "There are 1 budget categories.\n" +
                "_______________\n");


    }


    @Test
    void findBudget_invalidBudget_throwError() {
        String budgetName = "transport";
        Double budgetLimit = 10.0;
        budgetAction.addBudget(budgetName, budgetLimit);
        assertEquals(budgetList.size(), 1);
        assert budgetList.size() == 1 : "budget creation error";
        PrintStream originalStream;
        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
        PrintStream newStream = new PrintStream(testOutput);
        originalStream = System.out;
        System.setOut(newStream);
        budgetAction.findBudget("food");
        System.setOut(originalStream);
        assertEquals(testOutput, "_______________\n" +
                "This budget does not exist!\n" +
                "_______________\n");


    }

    @Test
    void findBudget_duplicateKeyword_success() {
        String budgetName1 = "school work";
        Double budgetLimit1 = 10.0;
        budgetAction.addBudget(budgetName1, budgetLimit1);
        assertEquals(budgetList.size(), 1);
        assert budgetList.size() == 1 : "budget creation error";

        String budgetName2 = "house work";
        Double budgetLimit2 = 10.0;
        budgetAction.addBudget(budgetName2, budgetLimit2);
        assertEquals(budgetList.size(), 2);
        assert budgetList.size() == 2 : "budget creation error";
        PrintStream originalStream;
        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
        PrintStream newStream = new PrintStream(testOutput);
        originalStream = System.out;
        System.setOut(newStream);
        budgetAction.findBudget("work");
        System.setOut(originalStream);
        assertEquals(testOutput, "_______________\n" +
                "1. Name: \"school work\" Limit: $10.00\n" +
                "2. Name: \"house work\" Limit: $10.00\n" +
                "There are 2 budget categories.\n" +
                "_______________\n");


    }

}
