package seedu.duke;

import java.util.*;

public class InputChecker {

    static boolean checkKeywordValid(String input) {
        int i = 0;
        while (i < Constants.keywords.length){
            if (Objects.equals(input, Constants.keywords[i])){
                return true;
            }
            i++;
        }
        return false;

    }

    static boolean checkCategoryValid(String input) {
        int i = 0;
        while (i < Constants.categories.length){
            if (Objects.equals(input, Constants.categories[i])){
                return true;
            }
            i++;
        }
        return false;

    }



}
