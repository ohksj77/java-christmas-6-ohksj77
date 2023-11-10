package christmas.domain.dicountpolicy;

import christmas.constant.DiscountPolicyType;
import christmas.domain.DiscountDetail;
import christmas.domain.Money;
import christmas.domain.VisitDate;

import java.time.LocalDate;

public class DdayDiscountPolicy implements DiscountPolicy {

    private static final LocalDate BEFORE_START_DATE = LocalDate.of(2023, 11, 30);
    private static final LocalDate AFTER_END_DATE = LocalDate.of(2023, 12, 26);
    private static final LocalDate START_DATE = LocalDate.of(2023, 12, 1);
    private static final int INIT_MONEY = 1000;
    private static final int MONEY_UNIT_PER_DAY = 100;
    private final VisitDate visitDate;
    private final DiscountCondition discountCondition;

    public DdayDiscountPolicy(final VisitDate visitDate) {
        this.visitDate = visitDate;
        this.discountCondition =
                () -> {
                    final LocalDate date = this.visitDate.toLocalDate();
                    return date.isAfter(BEFORE_START_DATE) && date.isBefore(AFTER_END_DATE);
                };
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
        final int discountAmount =
                INIT_MONEY + visitDate.compareDate(START_DATE) * MONEY_UNIT_PER_DAY;
        return toDiscountDetail(discountAmount);
    }

    private DiscountDetail toDiscountDetail(final int amount) {
        return new DiscountDetail(amount, DiscountPolicyType.D_DAY);
    }
}
