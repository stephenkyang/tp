package seedu.duke.action;

import java.util.ArrayList;
import java.util.Objects;

import seedu.duke.Ui;
import seedu.duke.model.Expense;
import seedu.duke.util.Messages;

//@@author tzixi
public class ExpenseUIResponse {
    private Ui ui;

    public ExpenseUIResponse(Ui ui) {
        this.ui = ui;
    }

    public void printExpenseAddSuccessful(Expense expense, int count) {
        String msg = String.format(Messages.EXPENSE_ADD_SUCCESSFUL.toString(), expense.getName());
        String countMsg = String.format(Messages.EXPENSE_NUMBER_OF.toString(), count);
        ui.printMessage(msg, countMsg);
    }

    public void printExpenseDelSuccessful(Expense expense, int count) {
        String msg = String.format(Messages.EXPENSE_DEL_SUCCESSFUL.toString(), expense.getName());
        String countMsg = String.format(Messages.EXPENSE_NUMBER_OF.toString(), count);
        ui.printMessage(msg, countMsg);
    }

    public void printListExpenses(ArrayList<Expense> expenses) {
        ArrayList<String> msgs = new ArrayList<String>();
        int i = 1;
        for (Expense e : expenses) {
            if (e != null) {
                String msg = String.format(Messages.EXPENSE_PRINT_EXPENSE.toString(), i,
                        e.getDate(), e.getCategory(), e.getName(), e.getAmount());
                msgs.add(msg);
                i++;
            }
        }

        ui.printMessage(msgs.toArray(new String[0]));
    }

    public static double printRelatedExpenses(ArrayList<Expense> expenses, String budgetName) {
        int i = 1;
        double totalExpenseValue = 0;
        System.out.println("These are the expenses that are under the " + budgetName + " budget:");
        for (Expense expense : expenses) {
            if (expense != null) {
                if (Objects.equals(expense.getCategory(), budgetName)) {
                    System.out.println(i + ". " + expense.getName() + " with amount of $" + expense.getAmount());
                    totalExpenseValue += expense.getAmount();
                    i++;
                }
            }

        }
        return totalExpenseValue;
    }
}
