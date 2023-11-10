package christmas.io;

import christmas.constant.DiscountPolicyType;
import christmas.constant.ProgressMessage;
import christmas.domain.DiscountDetail;
import christmas.domain.DiscountResults;
import christmas.domain.Money;
import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class OutputFormatter {

    private static final String NEW_LINE = "\n";

    public String formatBenefitPreview(final VisitDate visitDate) {
        final LocalDate date = visitDate.toLocalDate();
        return String.format(
                ProgressMessage.BENEFIT_PREVIEW.toString(),
                date.getMonthValue(),
                date.getDayOfMonth());
    }

    public String formatBeforePriceSum(final OrderMenus orderMenus) {
        final Money money = orderMenus.beforePriceSum();
        return String.format(ProgressMessage.BEFORE_PRICE_SUM.toString(), money.toValue());
    }

    public String formatDiscountResults(final DiscountResults discountResults) {
        if (discountResults.hasNoDiscount()) {
            return DiscountPolicyType.NO_DISCOUNT.toString();
        }
        return discountResults.toDiscountDetails().stream()
                .filter(element -> !element.hasNoValue() && !element.isAllPolicy())
                .map(
                        element ->
                                String.format(
                                        element.toDiscountPolicyType().toString(),
                                        element.toDifferenceValue()))
                .collect(Collectors.joining(NEW_LINE));
    }

    public String formatDiscountDetail(final DiscountDetail discountDetail) {
        return String.format(ProgressMessage.MONEY.toString(), discountDetail.toDifferenceValue());
    }

    public String formatMoney(final Money money) {
        return String.format(ProgressMessage.MONEY.toString(), money.toValue());
    }
}
