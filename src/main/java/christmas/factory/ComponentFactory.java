package christmas.factory;

import christmas.controller.ChristmasController;
import christmas.io.InputManager;
import christmas.io.InputMapper;
import christmas.io.InputValidator;
import christmas.io.InputView;
import christmas.io.OutputView;

public enum ComponentFactory {
    INSTANCE;

    private final ChristmasController christmasController;

    ComponentFactory() {
        this.christmasController = new ChristmasController(outputView(), inputManager());
    }

    private InputManager inputManager() {
        return new InputManager(inputView(), inputMapper());
    }

    private InputMapper inputMapper() {
        return new InputMapper();
    }

    private InputView inputView() {
        return new InputView(inputValidator());
    }

    private InputValidator inputValidator() {
        return new InputValidator();
    }

    private OutputView outputView() {
        return new OutputView();
    }

    public ChristmasController christmasController() {
        return this.christmasController;
    }
}
