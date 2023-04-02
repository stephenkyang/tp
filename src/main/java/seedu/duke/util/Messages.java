package seedu.duke.util;

public enum Messages {
    LINE_DIVIDER("_______________"),
    APP_LOGO(System.lineSeparator() +
        " █▀▀█ █   █ █▀▀▄ █▀▀▀ █▀▀ ▀▀█▀▀    █▀▀█ █  █ █▀▀▄ █▀▀▄ █  █ " + System.lineSeparator() +
        " █▀▀▄ █   █ █  █ █ ▀█ █▀▀   █      █▀▀▄ █  █ █  █ █  █ █▄▄█ " + System.lineSeparator() +
        " █▄▄█  ▀▀▀  ▀▀▀  ▀▀▀▀ ▀▀▀   ▀      █▄▄█  ▀▀▀ ▀▀▀  ▀▀▀  ▄▄▄█"),
    INFO_WELCOME("Welcome to BudgetBuddy! What can I do for you?"),
    INFO_EXIT("Bye! Hope to see you soon!"),

    HELP_SHOW_COMMANDS("Budget Buddy helps you to manage your finances better." + System.lineSeparator()
        + "Budget Buddy has 4 main categories:" + System.lineSeparator()
        + "1. Budget" + System.lineSeparator()
        + "   - Choose how much money you want to allocate to a budget of your specified name" + System.lineSeparator()
        + "2. Expense" + System.lineSeparator()
        + "   - track how much money you have spent, and link it to a certain budget of yours" + System.lineSeparator()
        + "3. Deposit " + System.lineSeparator()
        + "   - track how much money you have earned or received" + System.lineSeparator()
        + "4. Stats " + System.lineSeparator()
        + "   - shows the progress on spending so far as well as the total number of deposits" + System.lineSeparator()
        + "To learn the specific commands for each category, input \"(category) help\""),

    BUDGET_BUDGET("%3d. %s ($%.2f/%.2f)"),
    BUDGET_DOES_NOT_EXIST("This budget does not exist!"),
    BUDGET_LIMIT_NEGATIVE("This limit cannot be negative!"),
    BUDGET_NAME_USED("This budget name is already in use!"),
    BUDGET_ADD_SUCCESSFUL("Successfully added %s with limit of $%.2f"),
    BUDGET_SET_SUCCESSFUL("Successfully modified %s to have a limit of $%.2f"),
    BUDGET_DELETE_SUCCESSFUL("Successfully deleted %s."),
    BUDGET_LIST("Your budget list for %s %d:"),
    BUDGET_LIST_NOTHING("You have no budgets."),
    BUDGET_NUMBER_OF("There are %d budget categories."),
    BUDGET_CURRENT_PROGRESS(System.lineSeparator() + "Here is your current progress on the budget: "),
    BUDGET_HELP_COMMANDS("1. You can add a monthly budget: " + System.lineSeparator()
        + "   budget add /c (category name) /l (spending limit)" + System.lineSeparator()
        + "2. You can modify an existing budget: " + System.lineSeparator()
        + "   budget set /c (category name) /l (spending limit)" + System.lineSeparator()
        + "3. You can remove a monthly budget: " + System.lineSeparator()
        + "   budget del /c (category name)" + System.lineSeparator()
        + "4. You can list and check each budget's status for a certain month/year: " + System.lineSeparator()
        + "   budget list [optional] /m (month) /y (year in YYYY format)"),

    DEPOSIT_DEPOSIT("%d. %s ($%.2f) on %s"),
    DEPOSIT_ADD_SUCCESSFUL("The following deposit has been added:"),
    DEPOSIT_DELETE_SUCCESSFUL("The following deposit has been removed:"),
    DEPOSIT_CLEAR_SUCCESSFUL("These are the deposits cleared:"),
    DEPOSIT_CLEAR_NOTHING("No deposits cleared."),
    DEPOSIT_FIND("Here are the deposits you searched:"),
    DEPOSIT_FIND_NOTHING("Could not find any deposits you were trying to search."),
    DEPOSIT_LIST_PREVIOUS("Here are your previous deposits:"),
    DEPOSIT_LIST_CURRENT("Here are your deposits for this month:"),
    DEPOSIT_LIST_RANGE_FROM("Here are your deposits from %s:"),
    DEPOSIT_LIST_RANGE_TO("Here are your deposits until %s:"),
    DEPOSIT_LIST_RANGE("Here are your deposits from %s to %s:"),
    DEPOSIT_LIST_NOTHING("You have no deposits."),
    DEPOSIT_HELP_COMMANDS("1. You can add an deposit by: " + System.lineSeparator()
        + "   deposit add /n (name) /a (amount) [optional] /d DD-MM-YYYY" + System.lineSeparator()
        + "2. You can delete a deposit: " + System.lineSeparator()
        + "   deposit del /n (deposit number)" + System.lineSeparator()
        + "3. You can list all deposits (by date): " + System.lineSeparator()
        + "   deposit list [optional] /f DD-MM-YYYY /t DD-MM-YYYY" + System.lineSeparator()
        + "4. You can search for deposits that include a certain keyword: " + System.lineSeparator()
        + "   deposit find /n (keyword)" + System.lineSeparator()
        + "5. You can clear deposits (by date): " + System.lineSeparator()
        + "   deposit clear [optional] /f DD-MM-YYYY /t DD-MM-YYYY"),

    EXPENSE_EXPENSE("%d. [%s] %s ($%.2f) on %s"),
    EXPENSE_ADD_SUCCESSFUL("The following expense has been added:"),
    EXPENSE_DELETE_SUCCESSFUL("The following expense has been removed:"),
    EXPENSE_CLEAR_SUCCESSFUL("These are the expenses cleared:"),
    EXPENSE_CLEAR_NOTHING("No expenses cleared."),
    EXPENSE_FIND("Here are the expenses you searched:"),
    EXPENSE_FIND_NOTHING("Could not find any expenses you were trying to search."),
    EXPENSE_LIST_ALL("all"),
    EXPENSE_LIST_PREVIOUS("Here are your previous expenses for %s:"),
    EXPENSE_LIST_CURRENT("Here are your expenses for this month for %s:"),
    EXPENSE_LIST_RANGE_FROM("Here are your expenses from %s for %s:"),
    EXPENSE_LIST_RANGE_TO("Here are your expenses until %s for %s:"),
    EXPENSE_LIST_RANGE("Here are your expenses from %s to %s for %s:"),
    EXPENSE_LIST_NOTHING("You have no expenses."),
    EXPENSE_HELP_COMMANDS("1. You can add an expense by: " + System.lineSeparator()
        + "   expense add /c (category name) /n (name) /a (amount) [optional] /d DD-MM-YYYY" + System.lineSeparator()
        + "2. You can delete an expense: " + System.lineSeparator()
        + "   expense del /n (expense number) " + System.lineSeparator()
        + "3. You can list all expenses (by category, date or both): " + System.lineSeparator()
        + "   expense list [optional] /c (category name) /f DD-MM-YYYY /t DD-MM-YYYY" + System.lineSeparator()
        + "4. You can search for expenses that include a certain keyword:" + System.lineSeparator()
        + "   expense find /n (keyword)" + System.lineSeparator()
        + "5. You can clear expenses (by category, date or both): " + System.lineSeparator()
        + "   expense clear [optional] /c (category name) /f DD-MM-YYYY /t DD-MM-YYYY"),

    STATS_PRINT_INTRO("Your current progress for %s %d:"),
    STATS_PRINT_DEPOSITS(System.lineSeparator() + "Deposits"),
    STATS_PRINT_EXPENSES(System.lineSeparator() + "Expenses"),
    STATS_PRINT_DEPOSITS_TOTAL("Total deposits: $%.2f"),
    STATS_PRINT_EXPENSES_TOTAL("Total expenses: $%.2f"),
    STATS_PRINT_BUDGET_PROGRESS(System.lineSeparator() + "Total budget progress: $%.2f/%.2f (+%.2f)"),
    STATS_PRINT_RIGHT_TRACK("Good job! You are on the right track!"),
    STATS_PRINT_OVERSPEND("Oh no! You seem to be spending too much!"),
    STATS_PRINT_NO_STATS("There are no stats currently! Please try another command!"),
    STATS_HELP_COMMANDS("1. You can show stats by: " + System.lineSeparator() +
        "   stats show [optional] /m (month) /y (year in YYYY format) /v (d for deposit, e for expense, de for both)");

    private final String message;

    private Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
