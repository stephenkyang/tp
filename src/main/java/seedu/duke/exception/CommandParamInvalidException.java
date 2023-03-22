package seedu.duke.exception;

import seedu.duke.command.Command;
import seedu.duke.command.CommandEnum;
import seedu.duke.util.ErrorMessages;

//@@author pinyoko573
public class CommandParamInvalidException extends BBException {
    private CommandEnum commandName;
    private String action;

    public CommandParamInvalidException(Command command) {
        this.commandName = command.getCommandName();

        //Solution below adapted from https://stackoverflow.com/questions/2601978/
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
        case DEPOSIT:
            message = getDepositMessage(action);
            break;
        case EXPENSE:
            message = getExpenseMessage(action);
            break;
        case STATS:
            message = getStatsMessage(action);
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

    private String getDepositMessage(String action) {
        switch(action) {
        case "add":
            return ErrorMessages.ERROR_DEPOSIT_ADD_INVALID_PARAM.toString();
        case "del":
            return ErrorMessages.ERROR_DEPOSIT_DEL_INVALID_PARAM.toString();
        case "clear":
            return ErrorMessages.ERROR_DEPOSIT_CLEAR_INVALID_PARAM.toString();
        default:
            return ErrorMessages.ERROR_UNKNOWN_INVALID_ACTION.toString();
        }
    }

    private String getExpenseMessage(String action) {
        switch(action) {
        case "add":
            return ErrorMessages.ERROR_EXPENSE_ADD_INVALID_PARAM.toString();
        case "del":
            return ErrorMessages.ERROR_EXPENSE_DEL_INVALID_PARAM.toString();
        default:
            return ErrorMessages.ERROR_UNKNOWN_INVALID_ACTION.toString();
        }
    }

    private String getStatsMessage(String action) {
        switch(action) {
        case "show":
            return ErrorMessages.ERROR_STATS_SHOW_INVALID_PARAM.toString();
        default:
            return ErrorMessages.ERROR_UNKNOWN_INVALID_ACTION.toString();
        }
    }
}
