package christmas.factory;

import christmas.controller.ChristmasController;
import christmas.io.OutputView;

public enum ComponentFactory {
    INSTANCE;

    private final ChristmasController christmasController;

    ComponentFactory() {
        this.christmasController = new ChristmasController(outputView());
    }

    private OutputView outputView() {
        return new OutputView();
    }

    public ChristmasController christmasController() {
        return this.christmasController;
    }
}
