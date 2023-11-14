package christmas.constant;

public enum DiscountPolicyType {
    D_DAY("크리스마스 디데이 할인: -%d원"),
    WEEKDAY("평일 할인: -%d원"),
    WEEKEND("주말 할인: -%d원"),
    SPECIAL("특별 할인: -%d원"),
    GIVEAWAY("증정 이벤트: -%d원"),
    NO_DISCOUNT("없음"),
    ALL("");

    private final String message;

    DiscountPolicyType(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }

    public boolean isAll() {
        return this == ALL;
    }
}
