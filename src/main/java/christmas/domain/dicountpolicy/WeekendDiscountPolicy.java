package christmas.domain.dicountpolicy;

import christmas.constant.DiscountPolicyType;
import christmas.domain.DiscountDetail;
import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;

public final class WeekendDiscountPolicy extends DiscountPolicy {

    private static final int DISCOUNT_PER_MENU = 2023;
    private final OrderMenus orderMenus;

    public WeekendDiscountPolicy(final VisitDate visitDate, final OrderMenus orderMenus) {
        super(() -> visitDate.isWeekend() && orderMenus.hasMainDish());
        this.orderMenus = orderMenus;
    }

    @Override
    protected DiscountDetail calculateDiscount() {
        final int discountAmount = orderMenus.mainDishNum() * DISCOUNT_PER_MENU;
        return toDiscountDetail(discountAmount, DiscountPolicyType.WEEKEND);
    }
}
