package christmas.controller;

import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;
import christmas.io.InputManager;
import christmas.io.OutputView;
import christmas.service.ChristmasService;

public class ChristmasController {

    private final OutputView outputView;
    private final InputManager inputManager;
    private final ChristmasService christmasService;

    public ChristmasController(
            final OutputView outputView,
            final InputManager inputManager,
            final ChristmasService christmasService) {
        this.outputView = outputView;
        this.inputManager = inputManager;
        this.christmasService = christmasService;
    }

    public void run() {
        outputView.printGreeting();
        createVisitDate();
        createOrderMenus();
        printBenefitPreview();
    }

    private void createVisitDate() {
        final VisitDate visitDate = inputManager.readVisitDate();
        christmasService.saveVisitDate(visitDate);
    }

    private void createOrderMenus() {
        final OrderMenus orderMenus = inputManager.readOrderMenus();
        christmasService.saveOrderMenus(orderMenus);
    }

    private void printBenefitPreview() {
        final VisitDate visitDate = christmasService.getVisitDate();
        outputView.printBenefitPreview(visitDate);
    }
}
