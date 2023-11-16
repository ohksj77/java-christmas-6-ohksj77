package christmas.domain.dicountpolicy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constant.Menu;
import christmas.domain.DiscountDetail;
import christmas.domain.Money;
import christmas.domain.OrderMenu;
import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("주말 할인 정책")
class WeekendDiscountPolicyTest {

    @DisplayName("할인 금액 요청시")
    @Nested
    class CalculateDiscountAmount {

        @DisplayName("주말이면서 메인 메뉴가 포함된 경우 적합한 할인 금액을 반환한다.")
        @Test
        void weekendAndHasMainDish() {
            // given
            final int purchaseAmount = 4;
            final WeekendDiscountPolicy weekendDiscountPolicy =
                    new WeekendDiscountPolicy(
                            new VisitDate(23),
                            new OrderMenus(
                                    List.of(new OrderMenu(Menu.BARBECUE_LIB, purchaseAmount))));

            // when
            final DiscountDetail discountDetail = weekendDiscountPolicy.calculateDiscountAmount();

            // then
            final int discountAmount = 2023;
            assertThat(discountDetail.toDifferenceValue())
                    .isEqualTo(discountAmount * purchaseAmount);
        }

        @DisplayName("주말이면서 메인 메뉴가 포함되지 않은 경우 할인 금액이 없도록 반환한다.")
        @Test
        void weekendAndNoMainDish() {
            // given
            final WeekendDiscountPolicy weekendDiscountPolicy =
                    new WeekendDiscountPolicy(
                            new VisitDate(23),
                            new OrderMenus(List.of(new OrderMenu(Menu.CHOCO_CAKE, 4))));

            // when
            final DiscountDetail discountDetail = weekendDiscountPolicy.calculateDiscountAmount();

            // then
            assertThat(discountDetail.hasNoDifference()).isTrue();
        }

        @DisplayName("주말이 아닌 경우 할인 금액이 없도록 반환한다.")
        @Test
        void weekday() {
            // given
            final WeekendDiscountPolicy weekendDiscountPolicy =
                    new WeekendDiscountPolicy(
                            new VisitDate(24),
                            new OrderMenus(List.of(new OrderMenu(Menu.BARBECUE_LIB, 4))));

            // when
            final DiscountDetail discountDetail = weekendDiscountPolicy.calculateDiscountAmount();

            // then
            assertThat(discountDetail.hasNoDifference()).isTrue();
        }
    }

    @DisplayName("할인 요청시 적합한 할인 금액을 반환한다.")
    @Test
    void discount() {
        // given
        final int purchaseAmount = 4;
        final WeekendDiscountPolicy weekendDiscountPolicy =
                new WeekendDiscountPolicy(
                        new VisitDate(23),
                        new OrderMenus(List.of(new OrderMenu(Menu.BARBECUE_LIB, purchaseAmount))));

        // when
        final int amount = 30000;
        final Money result = weekendDiscountPolicy.discount(new Money(amount));

        // then
        final int discountUnit = 2023;
        assertThat(result.toValue()).isEqualTo(amount - purchaseAmount * discountUnit);
    }
}
