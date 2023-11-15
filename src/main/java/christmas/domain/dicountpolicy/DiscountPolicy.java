package christmas.domain.dicountpolicy;

import christmas.constant.DiscountPolicyType;
import christmas.domain.DiscountDetail;
import christmas.domain.Money;

public abstract class DiscountPolicy {
    protected static final int NO_DISCOUNT_DIFFERENCE = 0;
    protected final DiscountCondition discountCondition;

    protected DiscountPolicy(final DiscountCondition discountCondition) {
        this.discountCondition = discountCondition;
    }

    public Money discount(final Money money) {
        return money.discount(calculateDiscountAmount());
    }

    public final DiscountDetail calculateDiscountAmount() {
        if (discountCondition.isProperToDiscount()) {
            return calculateDiscount();
        }
        return toDiscountDetail(NO_DISCOUNT_DIFFERENCE, DiscountPolicyType.NO_DISCOUNT);
    }

    protected final DiscountDetail toDiscountDetail(
            final int discountAmount, final DiscountPolicyType discountPolicyType) {
        return new DiscountDetail(discountAmount, discountPolicyType);
    }

    protected abstract DiscountDetail calculateDiscount();
}
