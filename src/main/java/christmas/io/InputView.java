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
        System.out.println(ProgressMessage.VISIT_DAY_REQUEST);
        return readInput(inputValidator::validateVisitDate);
    }

    public String readOrderMenus() {
        System.out.println(ProgressMessage.MENUS_REQUEST);
        return readInput(inputValidator::validateOrderMenus);
    }

    private String readInput(final Consumer<String> validation) {
        final String input = Console.readLine();
        validation.accept(input);
        return input;
    }
}
