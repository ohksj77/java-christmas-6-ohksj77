package christmas.domain.dicountpolicy;

import christmas.constant.DiscountPolicyType;
import christmas.constant.EventDate;
import christmas.domain.DiscountDetail;
import christmas.domain.VisitDate;

import java.time.LocalDate;

public final class DdayDiscountPolicy extends DiscountPolicy {

    private static final int INIT_MONEY = 1000;
    private static final int MONEY_UNIT_PER_DAY = 100;
    private final VisitDate visitDate;

    public DdayDiscountPolicy(final VisitDate visitDate) {
        super(
                () -> {
                    final LocalDate date = visitDate.toDate();
                    return date.isAfter(EventDate.BEFORE_D_DAY_START.toValue())
                            && date.isBefore(EventDate.AFTER_D_DAY_END.toValue());
                });
        this.visitDate = visitDate;
    }

    @Override
    protected DiscountDetail calculateDiscount() {
        final int discountAmount =
                INIT_MONEY
                        + visitDate.compareDate(EventDate.START_OF_EVENT.toValue())
                                * MONEY_UNIT_PER_DAY;
        return toDiscountDetail(discountAmount, DiscountPolicyType.D_DAY);
    }
}
