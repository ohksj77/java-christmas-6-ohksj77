package christmas.io;

import christmas.constant.ProgressMessage;
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
}
