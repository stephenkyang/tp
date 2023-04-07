package seedu.duke.exception;

import java.time.LocalDate;

import seedu.duke.util.ErrorMessages;

//@@author pinyoko573
public class CommandParamTypeInvalidException extends BBException {
    private String paramMessage;
    private String actionMessage;

    public CommandParamTypeInvalidException() {

    }

    public CommandParamTypeInvalidException(String paramName, Class<?> paramType) {
        paramMessage = getParamTypeInvalidMessage(paramName, paramType);
    }

    @Override
    public String getMessage() {
        return paramMessage + System.lineSeparator() + actionMessage;
    }

    private String getParamTypeInvalidMessage(String paramName, Class<?> paramType) {
        if (paramType.isAssignableFrom(int.class)) {
            return String.format(ErrorMessages.ERROR_INVALID_PARAM_INTEGER.toString(), paramName);
        }
        
        if (paramType.isAssignableFrom(double.class)) {
            return String.format(ErrorMessages.ERROR_INVALID_PARAM_DOUBLE.toString(), paramName);
        }
        
        if (paramType.isAssignableFrom(LocalDate.class)) {
            return String.format(ErrorMessages.ERROR_INVALID_PARAM_DATE.toString(), paramName);
        }
        
        if (paramType.isAssignableFrom(String.class)) {
            return String.format(ErrorMessages.ERROR_INVALID_PARAM_STRING.toString(), paramName);
        }

        return null;
    }

    public void setActionMessage(String actionMessage) {
        this.actionMessage = actionMessage;
    }
}
