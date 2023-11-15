package christmas.domain.dicountpolicy;

import christmas.constant.DiscountPolicyType;
import christmas.domain.DiscountDetail;
import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;

public final class WeekdayDiscountPolicy extends DiscountPolicy {

    private static final int DISCOUNT_PER_MENU = 2023;
    private final OrderMenus orderMenus;

    public WeekdayDiscountPolicy(final VisitDate visitDate, final OrderMenus orderMenus) {
        super(() -> visitDate.isWeekday() && orderMenus.hasDessert());
        this.orderMenus = orderMenus;
    }

    @Override
    protected DiscountDetail calculateDiscount() {
        final int discountAmount = orderMenus.dessertNum() * DISCOUNT_PER_MENU;
        return toDiscountDetail(discountAmount, DiscountPolicyType.WEEKDAY);
    }
}
