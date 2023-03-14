package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.processing.FilerException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import seedu.duke.model.Budget;
import seedu.duke.model.Deposit;
import seedu.duke.model.Expense;
import seedu.duke.util.DataAdapter;

public class Data {
    public ArrayList<Budget> budgetList;
    public ArrayList<Deposit> depositList;
    public ArrayList<Expense> expenseList;

    public Data() {
        budgetList = new ArrayList<Budget>();
        depositList = new ArrayList<Deposit>();
        expenseList = new ArrayList<Expense>();
    }

    public static Data importData() throws FilerException, FileNotFoundException {
        File file = new File("data.json");
        if (!file.exists()) {
            System.out.println("Creating a new file...");
            return new Data();
        }
        FileReader fileReader = new FileReader(file);

        Gson gson = new GsonBuilder().registerTypeAdapter(Data.class, new DataAdapter()).create();
        Data data = gson.fromJson(fileReader, Data.class);
        return data;
    }

    public void exportData() {
        try {
            Gson gson = new GsonBuilder().registerTypeAdapter(Data.class, new DataAdapter()).create();

            FileWriter fw = new FileWriter("data.json");
            String jsonString = gson.toJson(this);
            fw.write(jsonString);
            fw.flush();
            fw.close();
        } catch (IOException err) {
            // test
        }
    }
}
