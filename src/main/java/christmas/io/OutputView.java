package christmas.io;

import christmas.constant.ProgressMessage;
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
}
