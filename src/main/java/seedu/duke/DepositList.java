package seedu.duke;

import java.util.ArrayList;
import java.util.HashMap;

public class DepositList {
    private static final HashMap<String, Deposit> implementedDepositHashMap = new HashMap<String, Deposit>();

    public static void add(String name, int amount) {
        implementedDepositHashMap.put(name, new Deposit(name, amount));
    }
    public static void print() {
        int totalAmount = 0;
        for (Deposit deposit : implementedDepositHashMap.values()) {
            System.out.println(deposit.name + ", " + deposit.amount);
            totalAmount += deposit.amount;
        }
        System.out.println("Total amount: " + totalAmount);
    }

    public static void delete(String name) {
        Deposit removedDeposit = implementedDepositHashMap.remove(name);
        if (removedDeposit == null) {
            System.out.println(name + " wasn't in List");
        }
    }

}
