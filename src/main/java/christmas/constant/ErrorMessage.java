package christmas.constant;

public enum ErrorMessage {
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    NOT_NUMERIC("숫자를 입력해주세요."),
    NOT_SAVED("저장되지 않은 객체의 조회를 시도했습니다."),
    INVALID_ORDER_MENUS("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    NOT_POSITIVE_MONEY("금액은 음수가 될 수 없습니다."),
    NOT_POSITIVE_DIFFERENCE("차액은 음수가 될 수 없습니다."),
    INVALID_DISCOUNT_RESULTS("전체 할인 결과가 저장되지 않았습니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(final String message) {
        this.message = PREFIX + message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
