package seedu.duke.util;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;

import seedu.duke.exception.GlobalDateFromAfterToException;
import seedu.duke.exception.GlobalDateAfterTodayException;
import seedu.duke.exception.GlobalInvalidMonthYearException;

//@@author pinyoko573
public class Commons {
    /**
     * Checks if the month and year given are valid and
     * does not go after this month.
     * 
     * @param month Month of the given date. Must be between 1 and 12
     * @param year Year of the given date
     * @return LocalDate with same month and year, but day as last day of month
     * @throws GlobalInvalidMonthYearException if give month and year is after this month or invalid date
     */
    public static LocalDate isValidMonthYear(int month, int year) throws GlobalInvalidMonthYearException {
        try {
            // Set day to last day of month
            LocalDate testDate = LocalDate.of(year, month, 1);
            testDate = testDate.with(TemporalAdjusters.lastDayOfMonth());

            assert testDate.getMonthValue() == month : "Test date must be the month given";
            assert testDate.getYear() == year : "Test date must be the year given";

            LocalDate modifiedDateNow = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());

            // Check if requested month and year is before current
            if (!(testDate.isBefore(modifiedDateNow) || testDate.isEqual(modifiedDateNow))) {
                throw new GlobalDateAfterTodayException();
            }

            return testDate;
        } catch (DateTimeParseException | GlobalDateAfterTodayException err) {
            throw new GlobalInvalidMonthYearException();
        }
    }

    /**
     * Converts the month number to string which will be used for printing.
     * 
     * @param month Month of the given date. Must be between 1 and 12
     * @return short string of the given month (e.g. Jan)
     */
    public static String convertMonthToString(int month) {
        String monthString = new DateFormatSymbols().getShortMonths()[month - 1];
        
        return monthString;
    }

    /**
     * Parses the string date range (from and to) to type LocalDate.
     * It is guaranteed parseable as input is checked in CommandParser.
     * 
     * @param stringFrom Date string that can be parsed to LocalDate
     * @param stringTo Date string that can be parsed to LocalDate
     * @return List of dates containing the from and to date.
     */
    public static LocalDate[] parseDateRange(String stringFrom, String stringTo) {
        // from, to
        LocalDate[] dates = { null, null };

        if (stringFrom != null) {
            dates[0] = LocalDate.parse(stringFrom, Constants.ACCEPTABLE_DATE_FORMAT);
        }

        if (stringTo != null) {
            dates[1] = LocalDate.parse(stringTo, Constants.ACCEPTABLE_DATE_FORMAT);
        }

        return dates;
    }

    /**
     * Check if the from date is after to date, which makes the
     * date range invalid (should be the other way around).
     * 
     * @param from From date
     * @param to To date
     * @throws GlobalDateFromAfterToException
     */
    public static void checkFromDateIsAfterTo(LocalDate from, LocalDate to) throws GlobalDateFromAfterToException {
        // Check if from < to
        if (from.isAfter(to)) {
            throw new GlobalDateFromAfterToException();
        }
    }
}
