package christmas.io;

import christmas.constant.Giveaway;
import christmas.constant.ProgressMessage;
import christmas.domain.DiscountDetail;
import christmas.domain.DiscountResults;
import christmas.domain.Money;
import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;

public class OutputView {

    private final OutputFormatter outputFormatter;

    public OutputView(final OutputFormatter outputFormatter) {
        this.outputFormatter = outputFormatter;
    }

    public void printGreeting() {
        System.out.println(ProgressMessage.GREETING);
    }

    public void printBenefitPreview(final VisitDate visitDate) {
        System.out.println(outputFormatter.formatBenefitPreview(visitDate));
    }

    public void printOrderMenus(final OrderMenus orderMenus) {
        System.out.println(ProgressMessage.ORDERED_MENUS);
        System.out.println(orderMenus);
        System.out.println(outputFormatter.formatBeforePriceSum(orderMenus));
    }

    public void printGiveaway(final Giveaway giveaway) {
        System.out.println(ProgressMessage.GIVEAWAY);
        System.out.println(giveaway);
    }

    public void printDiscountResults(final DiscountResults discountResults) {
        System.out.println(ProgressMessage.BENEFIT_LOG);
        System.out.println(outputFormatter.formatDiscountResults(discountResults));
    }

    public void printDiscountSum(final DiscountDetail discountDetail) {
        System.out.println(ProgressMessage.DISCOUNT_SUM);
        System.out.println(outputFormatter.formatDiscountDetail(discountDetail));
    }

    public void printExpectedAmount(final Money money) {
        System.out.println(ProgressMessage.EXPECTED_AMOUNT);
        System.out.println(outputFormatter.formatMoney(money));
    }
}
