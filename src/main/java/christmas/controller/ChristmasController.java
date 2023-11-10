package christmas.controller;

import christmas.constant.EventBadge;
import christmas.constant.Giveaway;
import christmas.domain.DiscountDetail;
import christmas.domain.DiscountResults;
import christmas.domain.Money;
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
        findResult();
    }

    private void findResult() {
        findBenefitPreview();
        findOrderMenus();
        findGiveaway();
        findBenefits();
        findDiscountSum();
        findExpectedAmount();
        findEventBadge();
    }

    private void createVisitDate() {
        final VisitDate visitDate = inputManager.readVisitDate();
        christmasService.saveVisitDate(visitDate);
    }

    private void createOrderMenus() {
        final OrderMenus orderMenus = inputManager.readOrderMenus();
        christmasService.saveOrderMenus(orderMenus);
    }

    private void findBenefitPreview() {
        final VisitDate visitDate = christmasService.getVisitDate();
        outputView.printBenefitPreview(visitDate);
    }

    private void findOrderMenus() {
        final OrderMenus orderMenus = christmasService.getOrderMenus();
        outputView.printOrderMenus(orderMenus);
    }

    private void findGiveaway() {
        final Giveaway giveaway = christmasService.calculateGiveaway();
        outputView.printGiveaway(giveaway);
    }

    private void findBenefits() {
        final DiscountResults discountResults = christmasService.calculateBenefits();
        outputView.printDiscountResults(discountResults);
    }

    private void findDiscountSum() {
        final DiscountDetail discountDetail = christmasService.calculateDiscountSum();
        outputView.printDiscountSum(discountDetail);
    }

    private void findExpectedAmount() {
        final Money money = christmasService.checkExpectedAmount();
        outputView.printExpectedAmount(money);
    }

    private void findEventBadge() {
        final EventBadge eventBadge = christmasService.findEventBadge();
        outputView.printEventBadge(eventBadge);
    }
}
