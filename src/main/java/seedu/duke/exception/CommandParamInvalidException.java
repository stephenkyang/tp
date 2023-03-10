package seedu.duke.exception;

import seedu.duke.command.Command;
import seedu.duke.command.CommandEnum;
import seedu.duke.util.ErrorMessages;

public class CommandParamInvalidException extends BBException {
    private CommandEnum commandName;
    private String action;

    public CommandParamInvalidException(Command command) {
        this.commandName = command.getCommandName();

        // https://stackoverflow.com/questions/2601978/how-to-check-if-my-string-is-equal-to-null
        if (command.getAction() == null) {
            this.action = "";
        } else {
            this.action = command.getAction();
        }
    }

    @Override
    public String getMessage() {
        String message;

        switch(commandName) {
        case BUDGET:
            message = getBudgetMessage(action);
            break;
        default:
            message = ErrorMessages.ERROR_UNKNOWN_INVALID_ACTION.toString();
        }

        return message;
    }

    private String getBudgetMessage(String action) {
        switch(action) {
        case "add":
            return ErrorMessages.ERROR_BUDGET_ADD_INVALID_PARAM.toString();
        case "set":
            return ErrorMessages.ERROR_BUDGET_SET_INVALID_PARAM.toString();
        case "del":
            return ErrorMessages.ERROR_BUDGET_DEL_INVALID_PARAM.toString();
        default:
            return ErrorMessages.ERROR_UNKNOWN_INVALID_ACTION.toString();
        }
    }
}
