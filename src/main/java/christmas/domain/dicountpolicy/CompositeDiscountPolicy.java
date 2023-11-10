package christmas.domain.dicountpolicy;

import christmas.constant.DiscountPolicyType;
import christmas.domain.DiscountDetail;
import christmas.domain.DiscountResults;
import christmas.domain.Money;
import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CompositeDiscountPolicy implements DiscountPolicy {

    private final DiscountCondition mainDiscountCondition;
    private final List<DiscountPolicy> discountPolicies;
    private final DiscountResults discountResults;

    public CompositeDiscountPolicy(final VisitDate visitDate, final OrderMenus orderMenus) {
        this.mainDiscountCondition = orderMenus::isDiscountAvailable;
        this.discountPolicies = initializeDiscountPolicies(visitDate, orderMenus);
        this.discountResults = organizeResults();
    }

    private boolean isUnableToDiscount() {
        return !mainDiscountCondition.isProperToDiscount();
    }

    private List<DiscountPolicy> initializeDiscountPolicies(
            final VisitDate visitDate, final OrderMenus orderMenus) {

        return List.of(
                new DdayDiscountPolicy(visitDate),
                new WeekdayDiscountPolicy(visitDate, orderMenus),
                new WeekendDiscountPolicy(visitDate, orderMenus),
                new SpecialDiscountPolicy(visitDate));
    }

    private DiscountResults organizeResults() {
        final Map<DiscountPolicyType, DiscountDetail> results = calculateDiscounts();

        results.put(
                DiscountPolicyType.ALL,
                new DiscountDetail(discountSum(results), DiscountPolicyType.ALL));

        return new DiscountResults(results);
    }

    private Map<DiscountPolicyType, DiscountDetail> calculateDiscounts() {
        return discountPolicies.stream()
                .map(DiscountPolicy::calculateDiscountAmount)
                .collect(
                        Collectors.toMap(
                                DiscountDetail::toDiscountPolicyType, Function.identity()));
    }

    private int discountSum(final Map<DiscountPolicyType, DiscountDetail> results) {
        return results.values().stream().mapToInt(DiscountDetail::toDifferenceValue).sum();
    }

    @Override
    public DiscountDetail calculateDiscountAmount() {
        if (isUnableToDiscount()) {
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
