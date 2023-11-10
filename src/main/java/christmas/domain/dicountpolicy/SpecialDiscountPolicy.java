package christmas.domain.dicountpolicy;

import christmas.constant.DiscountPolicyType;
import christmas.domain.DiscountDetail;
import christmas.domain.Money;
import christmas.domain.VisitDate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.function.Predicate;

public class SpecialDiscountPolicy implements DiscountPolicy {

    private static final LocalDate CHRISTMAS = LocalDate.of(2023, 12, 25);
    private static final int DISCOUNT_UNIT = 1000;
    private final DiscountCondition discountCondition;
    private final Predicate<LocalDate> isSpecialDate;

    public SpecialDiscountPolicy(final VisitDate visitDate) {
        this.isSpecialDate =
                date -> date.getDayOfWeek().equals(DayOfWeek.SUNDAY) || date.equals(CHRISTMAS);
        this.discountCondition = () -> isSpecialDate.test(visitDate.toLocalDate());
    }

    @Override
    public DiscountDetail calculateDiscountAmount() {
        if (discountCondition.isProperToDiscount()) {
            return calculateDiscount();
        }
        return calculateDiscount();
    }

    @Override
    public Money discount(final Money money) {
        return money.discount(calculateDiscount());
    }

    private DiscountDetail calculateDiscount() {
        return new DiscountDetail(DISCOUNT_UNIT, DiscountPolicyType.SPECIAL);
    }
}
