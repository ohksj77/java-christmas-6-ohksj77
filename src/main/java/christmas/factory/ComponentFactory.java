package christmas.factory;

import christmas.controller.ChristmasController;
import christmas.io.InputManager;
import christmas.io.InputMapper;
import christmas.io.InputValidator;
import christmas.io.InputView;
import christmas.io.OutputView;
import christmas.repository.DomainRepository;
import christmas.service.ChristmasService;

public enum ComponentFactory {
    INSTANCE;

    private final ChristmasController christmasController;

    ComponentFactory() {
        this.christmasController =
                new ChristmasController(outputView(), inputManager(), christmasService());
    }

    private ChristmasService christmasService() {
        return new ChristmasService(domainRepository());
    }

    private DomainRepository domainRepository() {
        return new DomainRepository();
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
