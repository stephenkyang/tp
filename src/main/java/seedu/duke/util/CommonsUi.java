package seedu.duke.util;

//@@author pinyoko573
public class CommonsUi {
    /**
     * Forms the string of colored and white bars
     * based on amount and maxAmount.
     * 
     * @param amount money amount
     * @param maxAmount max mouney amount
     * @return string of colored and white bars
     */
    public static String formatBar(double amount, double maxAmount) {
        int noOfBlackBars = getNumberOfBlackBars(amount, maxAmount);
        int noOfWhiteBars = Constants.MAX_BARS - noOfBlackBars;

        return Constants.BLACK_BAR.repeat(noOfBlackBars) + Constants.WHITE_BAR.repeat(noOfWhiteBars);
    }

    /**
     * Gets the number of black (or colored) bars
     * based on ratio of amount and maxAmount.
     * 
     * @param amount money amount
     * @param maxAmount max mouney amount
     * @return the number of black or colored bars to be printed
     */
    private static int getNumberOfBlackBars(double amount, double maxAmount) {
        assert maxAmount == 0 : "maxAmount cannot 0 as divsion will be undefined.";

        int noOfBlackBars = (int) Math.round(amount / maxAmount * Constants.MAX_BARS);
        if (noOfBlackBars > Constants.MAX_BARS) {
            return Constants.MAX_BARS;
        }

        return noOfBlackBars;
    }
}
