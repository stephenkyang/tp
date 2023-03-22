package seedu.duke.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;

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
}
