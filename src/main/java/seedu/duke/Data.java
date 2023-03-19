package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import seedu.duke.exception.FileExportException;
import seedu.duke.exception.FileImportException;

import seedu.duke.model.Budget;
import seedu.duke.model.Deposit;
import seedu.duke.model.Expense;

import seedu.duke.util.Constants;
import seedu.duke.util.LocalDateAdapter;
import seedu.duke.util.WarningMessages;

//@@author pinyoko573
/**
 * Data class which stores the arraylist of budgets, deposits and expenses.
 * File Input and Output are also handled here.
 */
public class Data {
    private static final Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
        .setPrettyPrinting()
        .create();
    private static Logger logger = Logger.getLogger("File");

    private ArrayList<Budget> budgets;
    private ArrayList<Deposit> deposits;
    private ArrayList<Expense> expenses;

    public Data() {
        budgets = new ArrayList<Budget>();
        deposits = new ArrayList<Deposit>();
        expenses = new ArrayList<Expense>();
    }

    /**
     * Imports and returns data containing Budget, Deposit and Expense
     * using Gson. When file (.json) is not found, empty arraylists
     * will be used and file will be created when an action is performed.
     * 
     * @return Data containing Budget, Deposit and Expense data
     * @throws FileImportException File could not be opened for unknown reason
     */
    public static Data importData() throws FileImportException {
        try {
            File file = new File(Constants.FILE_NAME.toString());
            if (!file.exists()) {
                System.out.println(WarningMessages.WARNING_FILE_NOT_FOUND);
                logger.log(Level.WARNING, "File not found, using empty list.");
                return new Data();
            }

            FileReader fileReader = new FileReader(file);
            Data data = gson.fromJson(fileReader, Data.class);

            logger.log(Level.INFO, "File successfully loaded");
            return data;
        } catch (FileNotFoundException err) {
            throw new FileImportException();
        }
    }

    /**
     * Exports the data containing Budget, Deposit and Expense
     * using Gson and a file will be created/overwritten whenever
     * an action is performed.
     * 
     * @throws FileExportException File could not be saved for unknown reason
     */
    public void exportData() throws FileExportException {
        try {
            FileWriter fw = new FileWriter(Constants.FILE_NAME.toString());
            String jsonString = gson.toJson(this);

            fw.write(jsonString);
            fw.flush();
            fw.close();
            
            logger.log(Level.INFO, "File successfully saved");
        } catch (IOException err) {
            throw new FileExportException();
        }
    }

    /**
     * Returns the list of budgets.
     * 
     * @return budgets ArrayList of budget
     */
    public ArrayList<Budget> getBudgets() {
        return budgets;
    }

    /**
     * Returns the list of deposits.
     * 
     * @return deposits ArrayList of deposit
     */
    public ArrayList<Deposit> getDeposits() {
        return deposits;
    }

    /**
     * Returns the list of Expenses.
     * 
     * @return expenses ArrayList of expense
     */
    public ArrayList<Expense> getExpenses() {
        return expenses;
    }
}
