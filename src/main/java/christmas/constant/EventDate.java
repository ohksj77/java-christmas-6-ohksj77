package christmas.constant;

import java.time.LocalDate;

public enum EventDate {
    CHRISTMAS(LocalDate.of(2023, 12, 25)),
    BEFORE_D_DAY_START(LocalDate.of(2023, 11, 30)),
    AFTER_D_DAY_END(LocalDate.of(2023, 12, 26)),
    START_OF_DECEMBER(LocalDate.of(2023, 12, 1));

    private final LocalDate value;

    EventDate(final LocalDate value) {
        this.value = value;
    }

    public LocalDate toValue() {
        return this.value;
    }
}
