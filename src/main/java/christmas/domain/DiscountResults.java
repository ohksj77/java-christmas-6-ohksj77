package christmas.domain;

import christmas.constant.DiscountPolicyType;
import christmas.constant.ErrorMessage;

import java.util.List;

public final class DiscountResults {

    private final List<DiscountDetail> discountDetails;

    public DiscountResults(final List<DiscountDetail> discountDetails) {
        this.discountDetails = List.copyOf(discountDetails);
    }

    public DiscountResults() {
        this.discountDetails = List.of();
    }

    public DiscountDetail getDiscountDetailSum() {
        return discountDetails.stream()
                .filter(discountDetail -> discountDetail.hasDiscountPolicy(DiscountPolicyType.ALL))
                .findFirst()
                .orElseThrow(
                        () ->
                                new IllegalStateException(
                                        ErrorMessage.INVALID_DISCOUNT_RESULTS.toString()));
    }

    public boolean hasNoDiscount() {
        return discountDetails.isEmpty() || getDiscountDetailSum().hasNoValue();
    }

    public List<DiscountDetail> toDiscountDetails() {
        return List.copyOf(discountDetails);
    }
}
