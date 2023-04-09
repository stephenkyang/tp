package seedu.duke.test.deposit;

import org.junit.jupiter.api.Test;
import seedu.duke.Ui;
import seedu.duke.action.DepositAction;
import seedu.duke.exception.BBException;
import seedu.duke.exception.GlobalDateAfterTodayException;
import seedu.duke.exception.GlobalDateFromAfterToException;
import seedu.duke.exception.GlobalInvalidNumberException;
import seedu.duke.model.Deposit;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//@@author stephenkyang

public class DepositTest {
    ArrayList<Deposit> depositList = new ArrayList<Deposit>();
    Ui ui = new Ui();
    DepositAction depositAction = new DepositAction(depositList, ui);
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }


    public void restoreStreams() {
        System.setOut(originalOut);
    }


    @Test
    void addAndDelete() {
        String depositName = "haha";
        double depositAmount = 300.00;
        LocalDate depositDate = LocalDate.now();
        try {
            depositAction.addDeposit(depositName, depositAmount, depositDate);
            assert depositList.size() != 0 : "add failed";
            assertEquals(1, depositList.size());
        
            depositAction.deleteDeposit(1);
        } catch (GlobalInvalidNumberException | GlobalDateAfterTodayException e) {
            fail();
        }
        assert depositList.size() != 1 : "delete failed";
        assertEquals(0, depositList.size());
    }


    @Test
    void simpleClear() {
        String[] depositNames = {"haha", "haha2"};
        double depositAmount = 300.00;
        LocalDate depositDate = LocalDate.now();
        try {
            for (String name : depositNames) {
                depositAction.addDeposit(name, depositAmount, depositDate);
            }
        } catch (GlobalDateAfterTodayException e) {
            fail();
        }

        assert depositList.size() != 0 : "add failed";
        assertEquals(2, depositList.size());
        try {
            depositAction.clearDeposits(depositDate, depositDate);
        } catch (GlobalDateFromAfterToException e) {
            fail();
        }
        assert depositList.size() != 2 : "clear failed";
        assertEquals(0, depositList.size());
    }


    @Test
    void complexClear() {
        String[] depositNames = {"haha", "haha2", "haha3", "haha4"};
        double depositAmount = 300.00;
        LocalDate[] depositDate = {LocalDate.of(2002, 1,1), LocalDate.now(),
                LocalDate.now(), LocalDate.now()};
        try {
            for (int i = 0; i < depositNames.length ; i++) {
                depositAction.addDeposit(depositNames[i], depositAmount, depositDate[i]);
            }
        } catch (GlobalDateAfterTodayException e) {
            fail();
        }

        assert depositList.size() != 0 : "add failed";
        assertEquals(4, depositList.size());
        try {
            depositAction.clearDeposits(depositDate[0], depositDate[0]);
        } catch (GlobalDateFromAfterToException e) {
            fail();
        }
        assert depositList.size() != 4 : "clear failed";
        assertEquals(3, depositList.size());
    }


    @Test
    void simpleFind() {
        String[] depositNames = {"haha", "haha2", "haha3", "haha4"};
        double depositAmount = 300.00;
        LocalDate[] depositDate = {LocalDate.of(2002, 1,1),
                LocalDate.of(2023, 4,9),
                LocalDate.now(), LocalDate.now()};
        try {
            for (int i = 0; i < depositNames.length ; i++) {
                depositAction.addDeposit(depositNames[i], depositAmount, depositDate[i]);
            }
        } catch (GlobalDateAfterTodayException e) {
            fail();
        }

        assert depositList.size() != 0 : "add failed";
        assertEquals(4, depositList.size());
        try {
            depositAction.clearDeposits(depositDate[0], depositDate[0]);
        } catch (GlobalDateFromAfterToException e) {
            fail();
        }
        assert depositList.size() != 4 : "clear failed";
        assertEquals(3, depositList.size());





        setUpStreams();
        depositAction.findDeposits("haha2");


        assertEquals("_______________\n" +
                "Here are the deposits you searched:\n" +
                "Deposit No 2. haha2 ($300.00) on 09 Apr 2023\n" +
                "_______________\n", outContent.toString());
        restoreStreams();
    }



    //@@author pinyoko573
    @Test
    void printDepositsTest() {
        try {
            depositAction.addDeposit("lottery 2", 2.00, LocalDate.now().minusDays(4));
            depositAction.addDeposit("lottery 3", 3.00, LocalDate.now().minusDays(3));
            depositAction.addDeposit("lottery 4", 4.00, LocalDate.now().minusDays(2));
            depositAction.addDeposit("lottery 5", 5.00, LocalDate.now().minusDays(1));
            depositAction.addDeposit("lottery 1", 1.00, LocalDate.now().minusDays(5));
            depositAction.addDeposit("lottery X", 100.00, LocalDate.now());

            depositAction.listDeposits();
            depositAction.listDepositsRange(LocalDate.now(), LocalDate.now());

            // Find deposit
            depositAction.findDeposits("x");

            // Filter expense by date
            ArrayList<Deposit> filterDate = DepositAction.filterDepositsByDate(depositList,
                LocalDate.now(), LocalDate.now());
            assertEquals(1, filterDate.size());

            // Sort expense by date
            ArrayList<Deposit> sortDate = DepositAction.sortDepositsByDate(depositList);
            assertEquals("lottery 1", sortDate.get(0).getName());

        } catch (BBException e) {
            fail();
        }
    }
}
