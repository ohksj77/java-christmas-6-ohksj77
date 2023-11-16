package christmas.io;

import christmas.constant.ErrorMessage;

import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern ORDER_MENUS_PATTER =
            Pattern.compile("^([^,\\d]+-\\d+,)*([^,\\d]+-\\d+)$");

    public void validateVisitDate(final String input) {
        if (isNotNumeric(input)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.toString());
        }
    }

    private boolean isNotNumeric(final String input) {
        return !input.chars().allMatch(Character::isDigit);
    }

    public void validateOrderMenus(final String input) {
        if (!ORDER_MENUS_PATTER.matcher(input).matches()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_MENUS.toString());
        }
    }
}
