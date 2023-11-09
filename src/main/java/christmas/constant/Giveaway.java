package christmas.constant;

import java.util.Arrays;

public enum Giveaway {
    PRESENT("샴페인 1개", 120_000),
    NONE("없음", 0);

    private final String message;
    private final int amount;

    Giveaway(final String message, final int amount) {
        this.message = message;
        this.amount = amount;
    }

    public static Giveaway valueOfPriceSum(final int priceSum) {
        return Arrays.stream(values())
                .filter(value -> value.amount < priceSum)
                .findAny()
                .orElse(NONE);
    }

    public String toString() {
        return this.message;
    }
}
