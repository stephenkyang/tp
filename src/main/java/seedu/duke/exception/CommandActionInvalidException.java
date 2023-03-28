package seedu.duke.exception;

import seedu.duke.command.Command;
import seedu.duke.command.CommandEnum;
import seedu.duke.util.ErrorMessages;

//@@author pinyoko573
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
        case DEPOSIT:
            return ErrorMessages.ERROR_DEPOSIT_INVALID_ACTION.toString();
        case EXPENSE:
            return ErrorMessages.ERROR_EXPENSE_INVALID_ACTION.toString();
        case STATS:
            return ErrorMessages.ERROR_STATS_INVALID_ACTION.toString();
        default:
            return ErrorMessages.ERROR_UNKNOWN_INVALID_ACTION.toString();
        }
    }
}
