package seedu.duke.expenses;

public class Expenses {
    String category;
    String name;
    int amount;
    String date;


    public Expenses(String category, String name, int amount, String date){
        this.category = category;
        this.name = name;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        //format is name, amount, /category
        return " " + name + " " + amount + " /" + category;
    }

}
