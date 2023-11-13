package christmas.domain;

import christmas.constant.DiscountPolicyType;
import christmas.constant.ErrorMessage;

import java.util.List;

public final class DiscountDetails {

    private final List<DiscountDetail> elements;

    public DiscountDetails(final List<DiscountDetail> elements) {
        this.elements = List.copyOf(elements);
    }

    public DiscountDetails() {
        this.elements = List.of();
    }

    public DiscountDetail getDiscountDetailSum() {
        return elements.stream()
                .filter(discountDetail -> discountDetail.hasDiscountPolicy(DiscountPolicyType.ALL))
                .findFirst()
                .orElseThrow(
                        () ->
                                new IllegalStateException(
                                        ErrorMessage.INVALID_DISCOUNT_RESULTS.toString()));
    }

    public boolean hasNoDiscount() {
        return elements.isEmpty() || getDiscountDetailSum().hasNoDifference();
    }

    public List<DiscountDetail> toDiscountDetails() {
        return List.copyOf(elements);
    }
}
