package christmas.constant;

public enum ProgressMessage {
    GREETING("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    VISIT_DAY_REQUEST("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    MENUS_REQUEST("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

    private final String message;

    ProgressMessage(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
