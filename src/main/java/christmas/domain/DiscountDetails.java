package christmas.domain;

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

    public DiscountDetail calculateDiscountSum() {
        return elements.stream()
                .filter(DiscountDetail::isAllPolicy)
                .findFirst()
                .orElseThrow(
                        () ->
                                new IllegalStateException(
                                        ErrorMessage.INVALID_DISCOUNT_RESULTS.toString()));
    }

    public boolean hasNoDiscount() {
        return elements.isEmpty() || calculateDiscountSum().hasNoDifference();
    }

    public List<DiscountDetail> toElements() {
        return List.copyOf(elements);
    }
}
