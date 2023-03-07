package seedu.duke;

import java.util.Objects;

public class BudgetParser {
    public static String command;

    /**
     * To determine the next method to run based on the user input
     *
     * @param command User input command that determines what budget function will run
     */


    public static void respondToBudgetInput(String command) {

        try {
            String[] stringArray = command.split(" ");
            String keyword = stringArray[1];
            System.out.println(keyword + "is the key");

            if (Objects.equals(keyword, "list")){
                BudgetList.printTaskList();
            }
            else {
                String budgetName = stringArray[3];
                switch (keyword) {

                case "del":

                    BudgetList.deleteBudget(budgetName);
                    break;

                case "set":
                    if (!BudgetList.duplicateBudgetName(budgetName)) {
                        double budgetLimit = Double.parseDouble(stringArray[5]);
                        System.out.println("name is " + budgetName + "  amount is  " + budgetLimit);
                        BudgetList.createBudget(budgetName, budgetLimit);
                    }
                    else {
                         System.out.println("Budget already exists!");
                    }

                    break;


                default:
                    invalidInputReponse();
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Budget limit is not a number!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ERROR! Invalid command!");
        }

    }

    private static void invalidInputReponse() {
        System.out.println("This is an invalid input!");
    }

}
