package christmas.io;

import christmas.constant.DiscountPolicyType;
import christmas.constant.ProgressMessage;
import christmas.domain.DiscountDetail;
import christmas.domain.DiscountDetails;
import christmas.domain.Money;
import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class OutputFormatter {

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

    public String formatDiscountDetails(final DiscountDetails discountDetails) {
        if (discountDetails.hasNoDiscount()) {
            return DiscountPolicyType.NO_DISCOUNT.toString();
        }
        return discountDetails.toDiscountDetails().stream()
                .filter(element -> !element.hasNoDifference() && !element.isAllPolicy())
                .map(
                        element ->
                                String.format(
                                        element.toDiscountPolicyType().toString(),
                                        element.toDifferenceValue()))
                .collect(Collectors.joining(ProgressMessage.NEW_LINE.toString()));
    }

    public String formatDiscountDetail(final DiscountDetail discountDetail) {
        if (discountDetail.hasNoDifference()) {
            return String.format(
                    ProgressMessage.POSITIVE_MONEY.toString(), discountDetail.toDifferenceValue());
        }
        return String.format(
                ProgressMessage.NEGATIVE_MONEY.toString(), discountDetail.toDifferenceValue());
    }

    public String formatMoney(final Money money) {
        return String.format(ProgressMessage.POSITIVE_MONEY.toString(), money.toValue());
    }
}
