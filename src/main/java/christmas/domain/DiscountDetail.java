package christmas.domain;

import christmas.constant.DiscountPolicyType;

public final class DiscountDetail {

    private final Difference difference;
    private final DiscountPolicyType discountPolicyType;

    public DiscountDetail(final Integer difference, final DiscountPolicyType discountPolicyType) {
        this.difference = new Difference(difference);
        this.discountPolicyType = discountPolicyType;
    }

    public int toDifferenceValue() {
        return this.difference.toValue();
    }

    public DiscountPolicyType toDiscountPolicyType() {
        return this.discountPolicyType;
    }

    public boolean hasNoValue() {
        return difference.hasNoValue();
    }

    public boolean hasDiscountPolicy(final DiscountPolicyType discountPolicyType) {
        return this.discountPolicyType == discountPolicyType;
    }

    public boolean isAllPolicy() {
        return this.discountPolicyType == DiscountPolicyType.ALL;
    }
}
