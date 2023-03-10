package seedu.duke;

import java.util.Objects;

public class BudgetParser {

    /**
     * To determine the next method to run based on the user input
     *
     * @param command User input command that determines what budget function will run
     */


    public static void respondToBudgetInput(String command) {

        try {


            String[] stringArray = command.split(" ");
            String category = stringArray[0];
            String keyword = stringArray[1];
            if (!InputChecker.checkCategoryValid(category)){
                System.out.println("Invalid first word!");
            }
            else if (!InputChecker.checkKeywordValid(keyword)){
                System.out.println("Invalid second word!");
            }


            else if (Objects.equals(keyword, "list")){
                BudgetList.printTaskList();
            }
            else {
                //String information = command.substring(10);
                //String[] budgetName = information.split("/l" );
                switch (keyword) {

                case "del":
                    String deleteBudgetName = command.substring(10);
                    BudgetList.deleteBudget(deleteBudgetName);
                    break;

                case "set":
                    String information = command.substring(14);
                    String[] setBudgetName = information.split("/l" );
                    if (!BudgetList.duplicateBudgetName(setBudgetName[1])) {
                        String[] budgetLimitString = information.split("/l" );
                        double budgetLimit = Double.parseDouble(budgetLimitString[1]);
                        System.out.println("name is " + setBudgetName[0] + "  amount is  " + budgetLimit);
                        BudgetList.createBudget(setBudgetName[0], budgetLimit);
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
            System.out.println("ERROR! Missing Information!");
        }

    }

    private static void invalidInputReponse() {
        System.out.println("This is an invalid input!");
    }

}
