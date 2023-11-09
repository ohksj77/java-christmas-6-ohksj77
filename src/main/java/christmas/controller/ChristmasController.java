package christmas.controller;

import christmas.io.OutputView;

public class ChristmasController {

    private final OutputView outputView;

    public ChristmasController(final OutputView outputView) {
        this.outputView = outputView;
    }

    public void run() {
        outputView.printGreeting();
    }
}
