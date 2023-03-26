package seedu.duke.util;

public class CommonsUi {
    public static String formatBar(double amount, double maxAmount) {
        int noOfBlackBars = getNumberOfBlackBars(amount, maxAmount);
        int noOfWhiteBars = Constants.MAX_BARS - noOfBlackBars;

        // Add colors based on number of bars
        String coloredBars = Constants.BLACK_BAR.repeat(noOfBlackBars) + Constants.ANSI_RESET;
        if (noOfBlackBars >= Constants.RED_BAR_MIN_COUNT) {
            coloredBars = Constants.ANSI_RED + coloredBars;
        } else if (noOfBlackBars >= Constants.YELLOW_BAR_MIN_COUNT) {
            coloredBars = Constants.ANSI_YELLOW + coloredBars;
        } else {
            coloredBars = Constants.ANSI_GREEN + coloredBars;
        }

        return coloredBars + Constants.WHITE_BAR.repeat(noOfWhiteBars);
    }

    private static int getNumberOfBlackBars(double amount, double maxAmount) {
        int noOfBlackBars = (int) Math.round(amount / maxAmount * Constants.MAX_BARS);
        if (noOfBlackBars > Constants.MAX_BARS) {
            return Constants.MAX_BARS;
        }

        return noOfBlackBars;
    }
}
