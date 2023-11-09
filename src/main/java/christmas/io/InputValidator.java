package christmas.io;

import christmas.constant.ErrorMessage;

public class InputValidator {

    public void validateVisitDate(final String input) {
        if (isNotNumeric(input)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_NUMERIC.toString());
        }
    }

    private boolean isNotNumeric(final String input) {
        return !input.chars().allMatch(Character::isDigit);
    }
}
