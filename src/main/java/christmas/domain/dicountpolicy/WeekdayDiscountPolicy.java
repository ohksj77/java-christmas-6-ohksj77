package christmas.domain.dicountpolicy;

import christmas.constant.DiscountPolicyType;
import christmas.domain.DiscountDetail;
import christmas.domain.Money;
import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;

public class WeekdayDiscountPolicy implements DiscountPolicy {

    private static final int DISCOUNT_PER_MENU = 2023;
    private final OrderMenus orderMenus;
    private final DiscountCondition discountCondition;

    public WeekdayDiscountPolicy(final VisitDate visitDate, final OrderMenus orderMenus) {
        this.orderMenus = orderMenus;
        this.discountCondition = () -> visitDate.isWeekday() && this.orderMenus.hasDessert();
    }

    @Override
    public DiscountDetail calculateDiscountAmount() {
        if (discountCondition.isProperToDiscount()) {
            return calculateDiscount();
        }
        return toDiscountDetail(NO_DISCOUNT_DIFFERENCE);
    }

    @Override
    public Money discount(final Money money) {
        return money.discount(calculateDiscountAmount());
    }

    private DiscountDetail calculateDiscount() {
        final int discountAmount = orderMenus.dessertNum() * DISCOUNT_PER_MENU;
        return toDiscountDetail(discountAmount);
    }

    private DiscountDetail toDiscountDetail(final int amount) {
        return new DiscountDetail(amount, DiscountPolicyType.WEEKDAY);
    }
}
