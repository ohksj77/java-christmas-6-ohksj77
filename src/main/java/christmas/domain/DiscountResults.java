package christmas.domain;

import christmas.constant.DiscountPolicyType;

import java.util.EnumMap;
import java.util.Map;

public class DiscountResults {

    private final Map<DiscountPolicyType, DiscountDetail> discountDetails;

    public DiscountResults(final Map<DiscountPolicyType, DiscountDetail> discountDetails) {
        this.discountDetails = new EnumMap<>(DiscountPolicyType.class);
        this.discountDetails.putAll(discountDetails);
    }

    public DiscountDetail getDiscountDetailSum() {
        return discountDetails.get(DiscountPolicyType.ALL);
    }

    public boolean hasNoDiscount() {
        final DiscountDetail discountDetail = discountDetails.get(DiscountPolicyType.ALL);
        return discountDetail.hasNoValue();
    }

    public Map<DiscountPolicyType, DiscountDetail> toDiscountDetails() {
        return Map.copyOf(discountDetails);
    }
}
