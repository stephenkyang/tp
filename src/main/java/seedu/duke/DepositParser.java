package seedu.duke;

public class DepositParser {

    public static String parseDepositCommand(String input) {
        String[] stringArray = input.split(" ");
        if (stringArray.length < 2) {
            System.out.println("Invalid number of arguments");
            //Eventually raise an error
            return null;
        }
        String deposit = stringArray[0]; //should be just "deposit" if correct logic
        String command = stringArray[1];
        if (!deposit.equals("deposit")) {
            System.out.println("Something with the logic is wrong where parseDepositCommand is called without user calling" +
                    "deposits");
            //Eventually raise an error
            return null;
        }
        String depositName;
        String depositAmount;
        switch (command) {
            case "add":
                if (stringArray.length != 6) {
                    System.out.println("Invalid number of arguments");
                    //Eventually raise an error
                    return null;
                }
                depositName = stringArray[3];
                depositAmount = stringArray[5];
                int amount;
                try {
                    amount = Integer.parseInt(depositAmount);
                } catch (NumberFormatException e) {
                    System.out.println("Amount inputted cannot be formated to int");
                    //eveutally raise error
                    return command;
                }
                DepositList.add(depositName, amount);
                System.out.println("Added " + depositName + "!");
                return command;
            case "del":
                if (stringArray.length != 3) {
                    System.out.println("Invalid number of arguments");
                    //Eventually raise an error
                    return command;
                }
                depositName = stringArray[2];
                DepositList.delete(depositName);
            case "list":
                DepositList.print();
                return command;
            case "bye":
                return command;
            default:
                System.out.println("Command not recongized");
                return command;
        }
    }

}
