package christmas.io;

import camp.nextstep.edu.missionutils.Console;

import christmas.constant.ProgressMessage;

public class InputView {

    private final InputValidator inputValidator;

    public InputView(final InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public String readVisitDate() {
        System.out.println(ProgressMessage.VISIT_DAY_REQUEST);
        final String input = Console.readLine();
        inputValidator.validateVisitDate(input);
        return input;
    }
}
