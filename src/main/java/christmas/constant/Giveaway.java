package christmas.constant;

import christmas.domain.Money;

import java.util.Arrays;
import java.util.Comparator;

public enum Giveaway {
    PRESENT("샴페인 1개", 120_000),
    NONE("없음", 0);

    private final String message;
    private final int amount;

    Giveaway(final String message, final int amount) {
        this.message = message;
        this.amount = amount;
    }

    public static Giveaway valueOfPriceSum(final Money money) {
        return Arrays.stream(values())
                .filter(value -> value.amount < money.toValue())
                .max(Comparator.comparingInt(before -> before.amount))
                .orElse(NONE);
    }

    public String toString() {
        return this.message;
    }
}
