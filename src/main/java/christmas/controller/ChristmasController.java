package christmas.controller;

import christmas.io.InputManager;
import christmas.io.OutputView;

public class ChristmasController {

    private final OutputView outputView;
    private final InputManager inputManager;

    public ChristmasController(final OutputView outputView, final InputManager inputManager) {
        this.outputView = outputView;
        this.inputManager = inputManager;
    }

    public void run() {
        outputView.printGreeting();
        inputManager.readVisitDate();
    }
}
