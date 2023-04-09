package seedu.duke.test.help;

import org.junit.jupiter.api.Test;
import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.command.HelpCommand;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

//@@author SaiChaitanya13

public class HelpCommandTest {

    Data data = new Data();
    Ui ui = new Ui();

    @Test
    public void execute_showHelpCommand_expectedNoErrorCaught() {
        HelpCommand helpCommand = new HelpCommand();
        assertDoesNotThrow(
                () -> helpCommand.execute(data, ui)
        );

    }


}
