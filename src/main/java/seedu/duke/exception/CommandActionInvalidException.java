package seedu.duke.exception;

import seedu.duke.command.Command;
import seedu.duke.command.CommandEnum;
import seedu.duke.util.ErrorMessages;

public class CommandActionInvalidException extends BBException {
    private CommandEnum commandName;

    public CommandActionInvalidException(Command command) {
        this.commandName = command.getCommandName();
    }

    @Override
    public String getMessage() {
        switch(commandName) {
        case BUDGET:
            return ErrorMessages.ERROR_BUDGET_INVALID_ACTION.toString();
        default:
            return ErrorMessages.ERROR_UNKNOWN_INVALID_ACTION.toString();
        }
    }
    
}
