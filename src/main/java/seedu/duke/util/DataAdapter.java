package seedu.duke.util;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import seedu.duke.Data;
import seedu.duke.model.Budget;
import seedu.duke.model.Deposit;
import seedu.duke.model.Expense;

public class DataAdapter extends TypeAdapter<Data> {

    @Override
    public void write(JsonWriter out, Data data) throws IOException {
        serializeData(out, data);
    }

    @Override
    public Data read(JsonReader in) throws IOException {
        in.beginObject();

        Data data = new Data();
        data.budgetList = readBudget(in);
        data.depositList = readDeposit(in);
        data.expenseList = readExpense(in);
        
        in.endObject();
        return data;
    }

    private void serializeData(JsonWriter out, Data data) throws IOException {
        out.beginObject();
        
        writeBudget(out, data.budgetList);
        writeDeposit(out, data.depositList);
        writeExpense(out, data.expenseList);

        out.endObject();
    }

    private void writeBudget(JsonWriter out, ArrayList<Budget> budgets) throws IOException {
        out.name("budget");

        out.beginArray();
        for (Budget budget : budgets) {
            out.beginObject();
            out.name("name").value(budget.getName());
            out.name("amount").value(budget.getAmount());
            out.endObject();
        }
        out.endArray();
    }

    private void writeDeposit(JsonWriter out, ArrayList<Deposit> deposits) throws IOException {
        out.name("deposit");

        out.beginArray();
        for (Deposit deposit : deposits) {
            out.beginObject();
            out.name("name").value(deposit.getName());
            out.name("amount").value(deposit.getAmount());
            out.endObject();
        }
        out.endArray();
    }

    private void writeExpense(JsonWriter out, ArrayList<Expense> expenses) throws IOException {
        out.name("expense");

        out.beginArray();
        for (Expense expense : expenses) {
            out.beginObject();
            out.name("category").value(expense.getCategory());
            out.name("name").value(expense.getName());
            out.name("amount").value(expense.getAmount());
            out.name("date").value(expense.getDate().toString());
            out.endObject();
        }
        out.endArray();
    }

    private ArrayList<Budget> readBudget(JsonReader in) throws IOException {
        ArrayList<Budget> budgets = new ArrayList<Budget>();
        in.nextName();
        
        in.beginArray();

        String category;
        double limit;

        while (in.hasNext()) {
            in.beginObject();

            in.nextName();
            category = in.nextString();
            in.nextName();
            limit = in.nextDouble();

            in.endObject();
            budgets.add(new Budget(category, limit));
        }
        in.endArray();
        
        return budgets;
    }

    private ArrayList<Deposit> readDeposit(JsonReader in) throws IOException {
        ArrayList<Deposit> deposits = new ArrayList<Deposit>();
        in.nextName();
        
        in.beginArray();

        String name;
        double amount;

        while (in.hasNext()) {
            in.beginObject();

            in.nextName();
            name = in.nextString();
            in.nextName();
            amount = in.nextDouble();

            in.endObject();
            deposits.add(new Deposit(name, amount, null));
        }
        in.endArray();
        
        return deposits;
    }

    private ArrayList<Expense> readExpense(JsonReader in) throws IOException {
        ArrayList<Expense> expenses = new ArrayList<Expense>();
        in.nextName();
        
        in.beginArray();

        String category;
        String name;
        double amount;
        String date;

        while (in.hasNext()) {
            in.beginObject();

            in.nextName();
            category = in.nextString();
            in.nextName();
            name = in.nextString();
            in.nextName();
            amount = in.nextDouble();
            in.nextName();
            date = in.nextString();

            in.endObject();
            expenses.add(new Expense(category, name, amount, null));
        }
        in.endArray();
        
        return expenses;
    }
}
