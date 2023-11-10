package christmas.domain.dicountpolicy;

import christmas.constant.DiscountPolicyType;
import christmas.constant.Giveaway;
import christmas.domain.DiscountDetail;
import christmas.domain.DiscountResults;
import christmas.domain.Money;
import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;

import java.util.List;
import java.util.stream.Collectors;

public class CompositeDiscountPolicy implements DiscountPolicy {

    private static final int DEFAULT_GIVEAWAY_DISCOUNT = 25000;
    private static final int MIN_PURCHASE_VALUE_FOR_DISCOUNT = 10000;
    private final DiscountCondition mainDiscountCondition;
    private final List<DiscountPolicy> discountPolicies;
    private final DiscountResults discountResults;

    public CompositeDiscountPolicy(
            final VisitDate visitDate, final OrderMenus orderMenus, final Giveaway giveaway) {
        this.mainDiscountCondition =
                () -> orderMenus.toBeforePriceSumValue() >= MIN_PURCHASE_VALUE_FOR_DISCOUNT;
        this.discountPolicies = initializeDiscountPolicies(visitDate, orderMenus);
        this.discountResults = organizeResultsIfAvailable(giveaway);
    }

    private List<DiscountPolicy> initializeDiscountPolicies(
            final VisitDate visitDate, final OrderMenus orderMenus) {

        return List.of(
                new DdayDiscountPolicy(visitDate),
                new WeekdayDiscountPolicy(visitDate, orderMenus),
                new WeekendDiscountPolicy(visitDate, orderMenus),
                new SpecialDiscountPolicy(visitDate));
    }

    private DiscountResults organizeResultsIfAvailable(final Giveaway giveaway) {
        if (isUnavailableToDiscount()) {
            return new DiscountResults();
        }
        return organizeResults(giveaway);
    }

    private boolean isUnavailableToDiscount() {
        return !mainDiscountCondition.isProperToDiscount();
    }

    private DiscountResults organizeResults(final Giveaway giveaway) {
        final List<DiscountDetail> results = calculateDiscounts();
        if (giveaway.exists()) {
            results.add(new DiscountDetail(DEFAULT_GIVEAWAY_DISCOUNT, DiscountPolicyType.GIVEAWAY));
        }
        results.add(new DiscountDetail(discountSum(results), DiscountPolicyType.ALL));
        return new DiscountResults(results);
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
    public DiscountDetail calculateDiscountAmount() {
        if (isUnavailableToDiscount()) {
            return new DiscountDetail(NO_DISCOUNT_DIFFERENCE, DiscountPolicyType.NO_DISCOUNT);
        }
        return discountResults.getDiscountDetailSum();
    }

    @Override
    public Money discount(final Money money) {
        Money result = money;
        for (final DiscountPolicy discountPolicy : discountPolicies) {
            result = discountPolicy.discount(result);
        }
        return result;
    }

    public DiscountResults toDiscountResults() {
        return this.discountResults;
    }
}
