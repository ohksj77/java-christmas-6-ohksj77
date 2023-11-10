package christmas.domain.dicountpolicy;

import christmas.domain.DiscountDetail;
import christmas.domain.Money;

public interface DiscountPolicy {
    int NO_DISCOUNT_DIFFERENCE = 0;

    DiscountDetail calculateDiscountAmount();

    Money discount(final Money money);
}
