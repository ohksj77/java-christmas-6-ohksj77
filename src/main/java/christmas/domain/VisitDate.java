package christmas.domain;

import christmas.constant.ErrorMessage;

import java.time.DayOfWeek;
import java.time.LocalDate;

public final class VisitDate {

    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;
    private static final int CURRENT_YEAR = 2023;
    private static final int CURRENT_MONTH = 12;
    private final LocalDate date;

    public VisitDate(final int date) {
        validate(date);
        this.date = LocalDate.of(CURRENT_YEAR, CURRENT_MONTH, date);
    }

    private void validate(final int date) {
        if (date < MIN_DATE || date > MAX_DATE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.toString());
        }
    }

    public LocalDate toLocalDate() {
        return this.date;
    }

    public boolean isWeekend() {
        final DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isWeekday() {
        return !isWeekend();
    }

    public int compareDate(final LocalDate date) {
        return this.date.compareTo(date);
    }
}
