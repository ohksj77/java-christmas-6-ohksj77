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

@DisplayName("주중 할인 정책")
class WeekdayDiscountPolicyTest {

    @DisplayName("할인 금액 요청시")
    @Nested
    class CalculateDiscountAmount {

        @DisplayName("주중이면서 디저트가 존재하는 경우 할인 금액을 반환한다.")
        @Test
        void weekdayAndHasDessert() {
            // given
            final int purchaseAmount = 3;
            final WeekdayDiscountPolicy weekdayDiscountPolicy =
                    new WeekdayDiscountPolicy(
                            new VisitDate(25),
                            new OrderMenus(
                                    List.of(new OrderMenu(Menu.CHOCO_CAKE, purchaseAmount))));

            // when
            final DiscountDetail discountDetail = weekdayDiscountPolicy.calculateDiscountAmount();

            // then
            final int discountUnit = 2023;
            assertThat(discountDetail.toDifferenceValue()).isEqualTo(purchaseAmount * discountUnit);
        }

        @DisplayName("주말인 경우 할인 금액이 없도록 반환한다.")
        @Test
        void weekend() {
            // given
            final WeekdayDiscountPolicy weekdayDiscountPolicy =
                    new WeekdayDiscountPolicy(
                            new VisitDate(23),
                            new OrderMenus(List.of(new OrderMenu(Menu.CHOCO_CAKE, 2))));

            // when
            final DiscountDetail discountDetail = weekdayDiscountPolicy.calculateDiscountAmount();

            // then
            assertThat(discountDetail.hasNoDifference()).isTrue();
        }

        @DisplayName("주중이지만 디저트가 포함되지 않은 경우 할인 금액이 없도록 반환한다.")
        @Test
        void weekdayButNoDessert() {
            // given
            final WeekdayDiscountPolicy weekdayDiscountPolicy =
                    new WeekdayDiscountPolicy(
                            new VisitDate(24),
                            new OrderMenus(List.of(new OrderMenu(Menu.BARBECUE_LIB, 2))));

            // when
            final DiscountDetail discountDetail = weekdayDiscountPolicy.calculateDiscountAmount();

            // then
            assertThat(discountDetail.hasNoDifference()).isTrue();
        }
    }

    @DisplayName("할인 요청시 정상적인 할인 정보는 반환한다.")
    @Test
    void discount() {
        // given
        final int purchaseAmount = 3;
        final WeekdayDiscountPolicy weekdayDiscountPolicy =
                new WeekdayDiscountPolicy(
                        new VisitDate(3),
                        new OrderMenus(List.of(new OrderMenu(Menu.CHOCO_CAKE, purchaseAmount))));

        // when
        final int beforeAmount = 10000;
        final Money result = weekdayDiscountPolicy.discount(new Money(beforeAmount));

        // then
        final int discountUnit = 2023;
        assertThat(result.toValue()).isEqualTo(beforeAmount - discountUnit * purchaseAmount);
    }
}
