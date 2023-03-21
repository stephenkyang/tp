package seedu.duke.action;

import seedu.duke.Ui;
import seedu.duke.model.Budget;
import seedu.duke.util.Messages;

import java.util.ArrayList;

//@@author chongyongrui

/**
 * Contains User Interface text responses when a budget method is run
 */
public class BudgetUIResponse {
    private Ui ui;

    public BudgetUIResponse(Ui ui) {
        this.ui = ui;
    }

    public void printBudgetDoesNotExist() {
        ui.printMessage(Messages.BUDGET_DOES_NOT_EXIST.toString());
    }

    public void printBudgetNameUsed() {
        ui.printMessage(Messages.BUDGET_NAME_USED.toString());
    }

    public void printBudgetLimitNegative() {
        ui.printMessage(Messages.BUDGET_LIMIT_NEGATIVE.toString());
    }

    public void printBudgetDelSuccessful(Budget budget, int count) {
        String msg = String.format(Messages.BUDGET_DELETE_SUCCESSFUL.toString(), budget.getName());
        String countMsg = String.format(Messages.BUDGET_NUMBER_OF.toString(), count);
        ui.printMessage(msg, countMsg);
    }

    public void printBudgetAddSuccessful(Budget budget, int count) {
        String msg = String.format(Messages.BUDGET_ADD_SUCCESSFUL.toString(), budget.getName(), budget.getAmount());
        String countMsg = String.format(Messages.BUDGET_NUMBER_OF.toString(), count);
        ui.printMessage(msg, countMsg);
    }

    public void printListBudgets(ArrayList<Budget> budgets) {
        ArrayList<String> msgs = new ArrayList<String>();
        int i = 1;
        for (Budget b : budgets) {
            if (b != null) {
                String msg = String.format(Messages.BUDGET_PRINT_BUDGET.toString(), i, b.getName(), b.getAmount());
                msgs.add(msg);
                i++;
            }
        }

        String budgetCount = String.format(Messages.BUDGET_NUMBER_OF.toString(), budgets.size());
        msgs.add(budgetCount);

        ui.printMessage(msgs.toArray(new String[0]));
    }

    public void printBudgetCommands() {
        String msg = String.format(Messages.BUDGET_HELP_COMMANDS.toString());
        ui.printMessage(msg);
    }


}
