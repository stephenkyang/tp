package seedu.duke.test;

import org.junit.jupiter.api.Test;
import seedu.duke.Ui;
import seedu.duke.action.DepositAction;
import seedu.duke.exception.GlobalDateAfterTodayException;
import seedu.duke.exception.GlobalInvalidNumberException;
import seedu.duke.model.Deposit;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//@@author stephenkyang
public class DepositTest {
    ArrayList<Deposit> depositList = new ArrayList<Deposit>();
    Ui ui = new Ui();
    DepositAction depositAction = new DepositAction(depositList, ui);

    @Test
    void addAndDeleteDeposit() {
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
}
