package christmas.domain.dicountpolicy;

@FunctionalInterface
public interface DiscountCondition {
    boolean isProperToDiscount();
}
