package seedu.duke;

import org.junit.jupiter.api.Test;

import seedu.duke.command.CommandParser;
import seedu.duke.exception.CommandActionInvalidException;
import seedu.duke.exception.CommandInvalidException;
import seedu.duke.exception.CommandParamInvalidException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandParserTest {

    @Test
    void commandParser_invalidCommand_test() {
        assertThrows(CommandInvalidException.class, () -> {
            CommandParser.parse("asd");
        });
    }

    @Test
    void commandParser_invalidAction_test() {
        assertThrows(CommandActionInvalidException.class, () -> {
            CommandParser.parse("budget asd");
        });
    }

    @Test
    void commandParser_invalidParameter_test() {
        assertThrows(CommandParamInvalidException.class, () -> {
            CommandParser.parse("budget add /i 300");
        });
    }

    @Test
    void commandParser_invalidParameterType_test() {
        assertThrows(CommandParamInvalidException.class, () -> {
            CommandParser.parse("budget add /c asd /i asd");
        });
    }
}
