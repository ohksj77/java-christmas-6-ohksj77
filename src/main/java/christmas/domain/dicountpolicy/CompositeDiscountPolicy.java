package christmas.domain.dicountpolicy;

import christmas.constant.DiscountPolicyType;
import christmas.constant.Giveaway;
import christmas.domain.DiscountDetail;
import christmas.domain.DiscountDetails;
import christmas.domain.Money;
import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;

import java.util.List;
import java.util.stream.Collectors;

public final class CompositeDiscountPolicy extends DiscountPolicy {

    private static final int DEFAULT_GIVEAWAY_DISCOUNT = 25000;
    private static final int MIN_PURCHASE_VALUE_FOR_DISCOUNT = 10000;
    private final List<DiscountPolicy> discountPolicies;
    private final DiscountDetails discountDetails;

    public CompositeDiscountPolicy(
            final VisitDate visitDate, final OrderMenus orderMenus, final Giveaway giveaway) {
        super(() -> orderMenus.toBeforeDiscountPriceSumValue() >= MIN_PURCHASE_VALUE_FOR_DISCOUNT);
        this.discountPolicies = initializeDiscountPolicies(visitDate, orderMenus);
        this.discountDetails = organizeResultsIfAvailable(giveaway);
    }

    private List<DiscountPolicy> initializeDiscountPolicies(
            final VisitDate visitDate, final OrderMenus orderMenus) {

        return List.of(
                new DdayDiscountPolicy(visitDate),
                new WeekdayDiscountPolicy(visitDate, orderMenus),
                new WeekendDiscountPolicy(visitDate, orderMenus),
                new SpecialDiscountPolicy(visitDate));
    }

    private DiscountDetails organizeResultsIfAvailable(final Giveaway giveaway) {
        if (isUnavailableToDiscount()) {
            return new DiscountDetails();
        }
        return organizeResults(giveaway);
    }

    private boolean isUnavailableToDiscount() {
        return !discountCondition.isProperToDiscount();
    }

    private DiscountDetails organizeResults(final Giveaway giveaway) {
        final List<DiscountDetail> results = calculateDiscounts();
        if (giveaway.exists()) {
            results.add(new DiscountDetail(DEFAULT_GIVEAWAY_DISCOUNT, DiscountPolicyType.GIVEAWAY));
        }
        results.add(new DiscountDetail(discountSum(results), DiscountPolicyType.ALL));
        return new DiscountDetails(results);
    }

    private List<DiscountDetail> calculateDiscounts() {
        return discountPolicies.stream()
                .map(DiscountPolicy::calculateDiscountAmount)
                .collect(Collectors.toList());
    }

    private int discountSum(final List<DiscountDetail> results) {
        return results.stream().mapToInt(DiscountDetail::toDifferenceValue).sum();
    }

    @Override
    public Money discount(final Money money) {
        Money result = money;
        for (final DiscountPolicy discountPolicy : discountPolicies) {
            result = discountPolicy.discount(result);
        }
        return result;
    }

    @Override
    protected DiscountDetail calculateDiscount() {
        return discountDetails.calculateDiscountSum();
    }

    public DiscountDetails toDiscountDetails() {
        return this.discountDetails;
    }
}
