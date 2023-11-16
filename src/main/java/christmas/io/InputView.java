package christmas.io;

import camp.nextstep.edu.missionutils.Console;

import christmas.constant.ProgressMessage;

import java.util.function.Consumer;

public class InputView {

    private final InputValidator inputValidator;

    public InputView(final InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public String readVisitDate() {
        return readInput(ProgressMessage.VISIT_DAY_REQUEST, inputValidator::validateVisitDate);
    }

    public String readOrderMenus() {
        return readInput(ProgressMessage.MENUS_REQUEST, inputValidator::validateOrderMenus);
    }

    private String readInput(
            final ProgressMessage inputRequest, final Consumer<String> validation) {
        System.out.println(inputRequest);
        final String input = Console.readLine();
        validation.accept(input);
        return input;
    }
}
