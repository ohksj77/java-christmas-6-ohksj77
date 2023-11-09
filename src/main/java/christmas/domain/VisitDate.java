package christmas.domain;

import christmas.constant.ErrorMessage;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitDate {

    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;
    private final LocalDate date;
    private final DayOfWeek dayOfWeek;

    public VisitDate(final int date) {
        validate(date);
        this.date = LocalDate.of(2023, 12, date);
        this.dayOfWeek = this.date.getDayOfWeek();
    }

    private void validate(final int date) {
        if (date < MIN_DATE || date > MAX_DATE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.toString());
        }
    }
}
