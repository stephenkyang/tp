package seedu.duke.test.command;

import org.junit.jupiter.api.Test;

import seedu.duke.command.CommandParser;
import seedu.duke.exception.CommandActionInvalidException;
import seedu.duke.exception.CommandInvalidException;
import seedu.duke.exception.CommandParamInvalidException;
import seedu.duke.exception.CommandParamSlashException;
import seedu.duke.exception.CommandParamTypeInvalidException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author pinyoko573

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
    void commandParser_invalidParameterMissing_test() {
        assertThrows(CommandParamInvalidException.class, () -> {
            CommandParser.parse("deposit add /n lottery");
        });
    }

    @Test
    void commandParser_invalidParameterIntType_test() {
        assertThrows(CommandParamTypeInvalidException.class, () -> {
            CommandParser.parse("deposit del /n a");
        });
    }

    @Test
    void commandParser_invalidParameterDoubleType_test() {
        assertThrows(CommandParamTypeInvalidException.class, () -> {
            CommandParser.parse("budget add /c asd /a asd");
        });
    }

    @Test
    void commandParser_invalidParameterDateType_test() {
        // date format must be dd-mm-yyyy
        assertThrows(CommandParamTypeInvalidException.class, () -> {
            CommandParser.parse("deposit add /n from ground /a 01-30-2020");
        });
    }

    @Test
    void commandParser_invalidParameterDateTypeYear_test() {
        // date format must be dd-mm-yyyy
        assertThrows(CommandParamTypeInvalidException.class, () -> {
            CommandParser.parse("deposit add /n from ground /a 04-04-23");
        });
    }

    @Test
    void commandParser_invalidParameterSlash_test() {
        // Any parameter value must not contain slash, including optional params
        assertThrows(CommandParamSlashException.class, () -> {
            CommandParser.parse("budget add /c /l 12.00");
        });

        assertThrows(CommandParamSlashException.class, () -> {
            CommandParser.parse("expense list /c a/");
        });
    }

    @Test
    void commandParser_validParameter_test() {
        assertDoesNotThrow(() -> {
            // normal input
            CommandParser.parse("budget add /c transport /a 2000");
            // input with number parsed to double, no optional params
            CommandParser.parse("deposit add /n pick up from ground /a 10");
            // input with double, optional params
            CommandParser.parse("expense add /c transport /n mrt /a 2.00 /d 30-01-2020");
        });
    }

    @Test
    void commandParser_validOptionalParameter_test() {
        assertDoesNotThrow(() -> {
            // include no optional parameters
            CommandParser.parse("budget list");
            // include only some optional parameters
            CommandParser.parse("budget list /m 3");
            // include all optional parameters
            CommandParser.parse("budget list /m 3 /y 2023");
        });
    }
}
