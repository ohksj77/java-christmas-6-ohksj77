package christmas.io;

import christmas.constant.ProgressMessage;
import christmas.domain.VisitDate;

import java.time.LocalDate;

public class OutputFormatter {

    public String formatBenefitPreview(final VisitDate visitDate) {
        final LocalDate date = visitDate.toLocalDate();
        return String.format(
                ProgressMessage.BENEFIT_PREVIEW.toString(),
                date.getMonthValue(),
                date.getDayOfMonth());
    }
}
