package christmas.domain;

import christmas.constant.ErrorMessage;

public final class PurchaseNum {

    private static final int MIN_PURCHASE_NUM = 1;
    private final Integer value;

    public PurchaseNum(final Integer value) {
        validate(value);
        this.value = value;
    }

    private void validate(final Integer value) {
        if (value < MIN_PURCHASE_NUM) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_MENUS.toString());
        }
    }

    public int toValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
