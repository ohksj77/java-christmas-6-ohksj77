package christmas.domain.dicountpolicy;

import christmas.constant.DiscountPolicyType;
import christmas.constant.EventDate;
import christmas.domain.DiscountDetail;
import christmas.domain.Money;
import christmas.domain.VisitDate;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialDiscountPolicy implements DiscountPolicy {

    private static final int DISCOUNT_UNIT = 1000;
    private final DiscountCondition discountCondition;

    public SpecialDiscountPolicy(final VisitDate visitDate) {
        this.discountCondition =
                () -> {
                    final LocalDate date = visitDate.toLocalDate();
                    return date.getDayOfWeek() == DayOfWeek.SUNDAY
                            || date.equals(EventDate.CHRISTMAS.toValue());
                };
    }

    @Override
    public DiscountDetail calculateDiscountAmount() {
        if (discountCondition.isProperToDiscount()) {
            return calculateDiscount(DISCOUNT_UNIT);
        }
        return calculateDiscount(NO_DISCOUNT_DIFFERENCE);
    }

    @Override
    public Money discount(final Money money) {
        return money.discount(calculateDiscountAmount());
    }

    private DiscountDetail calculateDiscount(final int discountAmount) {
        return new DiscountDetail(discountAmount, DiscountPolicyType.SPECIAL);
    }
}
