package seedu.duke.util;

public class CommonsUi {
    public static String formatBar(double amount, double maxAmount) {
        int noOfBlackBars = getNumberOfBlackBars(amount, maxAmount);
        int noOfWhiteBars = Constants.MAX_BARS - noOfBlackBars;

        return Constants.BLACK_BAR.repeat(noOfBlackBars) +
            Constants.WHITE_BAR.repeat(noOfWhiteBars);
    }

    private static int getNumberOfBlackBars(double amount, double maxAmount) {
        int noOfBlackBars = (int) Math.round(amount / maxAmount * Constants.MAX_BARS);
        if (noOfBlackBars > Constants.MAX_BARS) {
            return Constants.MAX_BARS;
        }

        return noOfBlackBars;
    }
}
