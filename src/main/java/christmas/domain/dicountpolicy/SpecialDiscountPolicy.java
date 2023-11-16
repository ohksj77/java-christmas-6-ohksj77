package christmas.domain.dicountpolicy;

import christmas.constant.DiscountPolicyType;
import christmas.constant.EventDate;
import christmas.domain.DiscountDetail;
import christmas.domain.VisitDate;

import java.time.DayOfWeek;
import java.time.LocalDate;

public final class SpecialDiscountPolicy extends DiscountPolicy {

    private static final int DISCOUNT_UNIT = 1000;

    public SpecialDiscountPolicy(final VisitDate visitDate) {
        super(
                () -> {
                    final LocalDate date = visitDate.toDate();
                    return date.getDayOfWeek() == DayOfWeek.SUNDAY
                            || date.equals(EventDate.CHRISTMAS.toValue());
                });
    }

    @Override
    protected DiscountDetail calculateDiscount() {
        return toDiscountDetail(DISCOUNT_UNIT, DiscountPolicyType.SPECIAL);
    }
}
