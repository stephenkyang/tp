package seedu.duke.util;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;

import seedu.duke.exception.GlobalDateFromAfterToException;
import seedu.duke.exception.GlobalDateAfterTodayException;
import seedu.duke.exception.GlobalInvalidMonthYearException;

public class Commons {
    public static LocalDate isValidMonthYear(int month, int year) throws GlobalInvalidMonthYearException {
        try {
            // Set day to last day of month
            LocalDate testDate = LocalDate.of(year, month, 1);
            testDate = testDate.with(TemporalAdjusters.lastDayOfMonth());

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

    public static String convertMonthToString(int month) {
        String monthString = new DateFormatSymbols().getShortMonths()[month - 1];
        
        return monthString;
    }

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

    public static void checkFromDateIsAfterTo(LocalDate from, LocalDate to) throws GlobalDateFromAfterToException {
        // Check if from < to
        if (from.isAfter(to)) {
            throw new GlobalDateFromAfterToException();
        }
    }
}
