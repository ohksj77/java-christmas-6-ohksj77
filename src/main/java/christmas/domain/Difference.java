package christmas.domain;

import christmas.constant.ErrorMessage;

public final class Difference {

    private static final int NONE = 0;
    private final Integer value;

    public Difference(final Integer value) {
        validate(value);
        this.value = value;
    }

    private void validate(final Integer value) {
        if (value < NONE) {
            throw new IllegalStateException(ErrorMessage.NOT_POSITIVE_DIFFERENCE.toString());
        }
    }

    public Integer toValue() {
        return this.value;
    }

    public boolean hasNoValue() {
        return value == NONE;
    }
}
