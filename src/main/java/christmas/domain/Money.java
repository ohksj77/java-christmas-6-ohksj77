package christmas.domain;

import christmas.constant.ErrorMessage;

public final class Money {

    private static final int MIN_MONEY = 0;
    private final Integer value;

    public Money(final Integer value) {
        validate(value);
        this.value = value;
    }

    private Money(final Money money, final Integer discountValue) {
        final int resultMoney = money.value - discountValue;
        validate(resultMoney);
        this.value = resultMoney;
    }

    private void validate(final Integer value) {
        if (value < MIN_MONEY) {
            throw new IllegalArgumentException(ErrorMessage.NOT_POSITIVE_MONEY.toString());
        }
    }

    public Integer toValue() {
        return this.value;
    }

    public Money discount(final DiscountDetail discountDetail) {
        return new Money(this, discountDetail.toDifferenceValue());
    }
}
