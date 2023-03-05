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
            String budgetName = stringArray[3];
            switch (keyword) {

            case "list":
                BudgetList.printTaskList();
                break;


            case "del":

                BudgetList.deleteBudget(budgetName);
                break;

            case "set":
                //boolean isValidDeadlineInput = checkSetBudgetInput(command);
                double budgetLimit = Double.parseDouble(stringArray[5]);
                BudgetList.createBudget(budgetName, budgetLimit);
                break;


            default:
                invalidInputReponse();
            }

        } catch (NumberFormatException e) {
            System.out.println("Budget limit is not a number!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Information is missing!");
        }

    }

    private static void invalidInputReponse() {
        System.out.println("This is an invalid input!");
    }

}
