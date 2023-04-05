package seedu.duke.test;

import org.junit.jupiter.api.Test;

import seedu.duke.command.CommandParser;
import seedu.duke.exception.CommandActionInvalidException;
import seedu.duke.exception.CommandInvalidException;
import seedu.duke.exception.CommandParamInvalidException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author chongyongrui

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
    void commandParser_invalidParameterDoubleType_test() {
        assertThrows(CommandParamInvalidException.class, () -> {
            CommandParser.parse("budget add /c asd /i asd");
        });
    }

    @Test
    void commandParser_invalidParameterDateType_test() {
        // date format must be dd-mm-yyyy
        assertThrows(CommandParamInvalidException.class, () -> {
            CommandParser.parse("deposit add /n from ground /a 01-30-2020");
        });
    }

    @Test
    void commandParser_validParameter_test() {
        assertDoesNotThrow(() -> {
            // normal input
            CommandParser.parse("budget add /c transport /l 2000");
            // input with number parsed to double, no optional params
            CommandParser.parse("deposit add /n pick up from ground /a 10");
            // input with double, optional params
            CommandParser.parse("expense add /c transport /n mrt /a 2.00 /d 30-01-2020");
        });
    }
}
