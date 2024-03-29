package christmas.controller;

import christmas.constant.EventBadge;
import christmas.constant.Giveaway;
import christmas.domain.DiscountDetail;
import christmas.domain.DiscountDetails;
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
        checkResult();
    }

    private void checkResult() {
        checkBenefitPreview();
        checkOrderMenus();
        checkGiveaway();
        checkBenefits();
        checkDiscountSum();
        checkExpectedAmount();
        checkEventBadge();
    }

    private void createVisitDate() {
        final VisitDate visitDate = inputManager.readVisitDate();
        christmasService.saveVisitDate(visitDate);
    }

    private void createOrderMenus() {
        final OrderMenus orderMenus = inputManager.readOrderMenus();
        christmasService.saveOrderMenus(orderMenus);
    }

    private void checkBenefitPreview() {
        final VisitDate visitDate = christmasService.getVisitDate();
        outputView.printBenefitPreview(visitDate);
    }

    private void checkOrderMenus() {
        final OrderMenus orderMenus = christmasService.getOrderMenus();
        outputView.printOrderMenus(orderMenus);
    }

    private void checkGiveaway() {
        final Giveaway giveaway = christmasService.calculateGiveaway();
        outputView.printGiveaway(giveaway);
    }

    private void checkBenefits() {
        final DiscountDetails discountDetails = christmasService.calculateBenefits();
        outputView.printDiscountDetails(discountDetails);
    }

    private void checkDiscountSum() {
        final DiscountDetail discountDetail = christmasService.calculateDiscountSum();
        outputView.printDiscountSum(discountDetail);
    }

    private void checkExpectedAmount() {
        final Money money = christmasService.checkExpectedAmount();
        outputView.printExpectedAmount(money);
    }

    private void checkEventBadge() {
        final EventBadge eventBadge = christmasService.findEventBadge();
        outputView.printEventBadge(eventBadge);
    }
}
