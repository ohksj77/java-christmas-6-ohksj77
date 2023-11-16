package christmas.constant;

import java.util.Arrays;
import java.util.Comparator;

public enum EventBadge {
    STAR(5_000, "별"),
    TREE(10_000, "트리"),
    SANTA(20_000, "산타"),
    NONE(0, "없음");

    private final int amount;
    private final String name;

    EventBadge(final int amount, final String name) {
        this.amount = amount;
        this.name = name;
    }

    public static EventBadge valueOfAmount(final int money) {
        return Arrays.stream(values())
                .filter(eventBadge -> eventBadge.amount <= money)
                .max(Comparator.comparing(EventBadge::toAmount))
                .orElse(NONE);
    }

    private int toAmount() {
        return this.amount;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
