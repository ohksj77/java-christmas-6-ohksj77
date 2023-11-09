package christmas.constant;

public enum ErrorMessage {
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    NOT_NUMERIC("숫자를 입력해주세요.");

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
