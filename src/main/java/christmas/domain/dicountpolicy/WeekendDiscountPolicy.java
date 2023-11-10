package christmas.domain.dicountpolicy;

import christmas.constant.DiscountPolicyType;
import christmas.domain.DiscountDetail;
import christmas.domain.Money;
import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;

public class WeekendDiscountPolicy implements DiscountPolicy {

    private static final int DISCOUNT_PER_MENU = 2023;
    private final OrderMenus orderMenus;
    private final DiscountCondition discountCondition;

    public WeekendDiscountPolicy(final VisitDate visitDate, final OrderMenus orderMenus) {
        this.orderMenus = orderMenus;
        this.discountCondition = () -> visitDate.isWeekend() && this.orderMenus.hasMainDish();
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
        final int discountAmount = orderMenus.mainDishNum() * DISCOUNT_PER_MENU;
        return toDiscountDetail(discountAmount);
    }

    private DiscountDetail toDiscountDetail(final int discountAmount) {
        return new DiscountDetail(discountAmount, DiscountPolicyType.WEEKEND);
    }
}
