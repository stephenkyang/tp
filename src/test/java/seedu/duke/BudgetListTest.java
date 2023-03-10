package seedu.duke;

class BudgetListTest {

}

// package seedu.duke;

// import org.junit.jupiter.api.Test;

// import java.util.ArrayList;

// import static org.junit.jupiter.api.Assertions.*;

// class BudgetListTest {
//     //public static ArrayList<Budget> budgets = new ArrayList<Budget>();

//     BudgetList bl = new BudgetList();

//     @Test
//     void createBudget_newBudget_newBudget() {
//         String budgetName = "good";
//         Double budgetLimit = 10.0;
//         bl.createBudget(budgetName,budgetLimit);
//         for ( Budget a : bl.budgets){
//             System.out.println(a.budgetName);
//         }
//         assertEquals(2, bl.budgets.size());
//     }


//     @Test
//     void duplicateBudgetName_newBudget_noError() {
//         BudgetList bl = new BudgetList();
//         bl.createBudget("food",0.00);
//         String budgetName = "food";
//         assertEquals(true, bl.duplicateBudgetName(budgetName));
//         budgetName = "transport";
//         assertEquals(false, bl.duplicateBudgetName(budgetName));
//     }



//     @Test
//     void deleteBudget() {
//         BudgetList bl = new BudgetList();
//         bl.createBudget("food",0.00);
//         bl.deleteBudget("drink");
//         assertEquals(1, bl.budgets.size());
//         bl.deleteBudget("food");
//         assertEquals(0, bl.budgets.size());

//     }



// }
