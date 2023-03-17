package seedu.duke.command;

import seedu.duke.exception.BBException;
import seedu.duke.exception.CommandActionInvalidException;
import seedu.duke.exception.CommandInvalidException;
import seedu.duke.exception.CommandParamInvalidException;
import seedu.duke.exception.CommandParamTypeInvalidException;
import seedu.duke.util.Pair;

public class CommandParser {
    public static Command parse(String input) throws BBException {
        CommandEnum commandName = getCommandName(input);

        Command command;
        switch (commandName) {
        case BUDGET:
            command = new BudgetCommand();
            break;
        case DEPOSIT:
            command = new DepositCommand();
            break;
        case EXPENSE:
            command = new ExpenseCommand();
            break;
        case STATS:
            command = new StatsCommand();
            break;
        case EXIT:
            command = new ExitCommand();
            break;
        case HELP:
            command = new HelpCommand();
            HelpCommand.showCommands();
            break;


        default:
            throw new CommandInvalidException();
        }

        // For commands that do not have any action
        if (command.getActions().length == 0) {
            return command;
        }

        // Check if action (given in input) exists
        String action = getAction(command, input);

        // Check if all parameters are valid based on action
        String[] requiredParams = getRequiredParams(command, action, input);

        command.set(action, requiredParams, null);
        return command;
    }

    private static CommandEnum getCommandName(String input) throws CommandInvalidException {
        try {
            String[] splitInput = input.split(" ", 2);
            CommandEnum commandName = CommandEnum.valueOf(splitInput[0].toUpperCase());
            return commandName;
        } catch (IllegalArgumentException err) {
            throw new CommandInvalidException();
        }
    }

    private static String getAction(Command command, String input) throws CommandActionInvalidException {
        String[] splitInput = input.split(" ", 3);
        try {
            String actionName = splitInput[1];
            int actionNo = command.getActionNo(actionName);
            if (actionNo != -1) {
                return actionName;
            }
            throw new CommandActionInvalidException(command);
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new CommandActionInvalidException(command);
        }
    }

    private static String[] getRequiredParams(Command command, String actionName, String input) throws BBException {
        int actionNo = command.getActionNo(actionName);
        Pair[] requiredParams = command.requiredParamsList[actionNo];
        String[] params = new String[requiredParams.length];

        try {
            int paramCount = 0;
            for (Pair param : requiredParams) {
                String paramName = param.getKey();
                Class<?> paramType = param.getValue();

                // Gets parameter value from the parameter syntax
                String paramValue = input.split(" " + paramName + " ")[1].split(" /")[0];

                // Check if the parameter value suits for the class type (e.g. int, string)
                validateParamType(paramValue, paramType);

                params[paramCount] = paramValue;
                paramCount++;
            }
        } catch (ArrayIndexOutOfBoundsException | CommandParamTypeInvalidException err) {
            throw new CommandParamInvalidException(command);
        }

        return params;
    }

    private static void validateParamType(String paramValue, Class<?> paramType) throws BBException {
        try {
            if (paramType.isAssignableFrom(int.class)) {
                Integer.parseInt(paramValue);
            } else if (paramType.isAssignableFrom(double.class)) {
                Double.parseDouble(paramValue);
            }
        } catch (NumberFormatException err) {
            throw new CommandParamTypeInvalidException();
        }
    }
}
