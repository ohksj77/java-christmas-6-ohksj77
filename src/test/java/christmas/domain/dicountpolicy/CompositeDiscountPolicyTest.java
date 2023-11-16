package christmas.domain.dicountpolicy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constant.Giveaway;
import christmas.constant.Menu;
import christmas.domain.DiscountDetail;
import christmas.domain.DiscountDetails;
import christmas.domain.Money;
import christmas.domain.OrderMenu;
import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("전체 할인 정책 관리 클래스")
class CompositeDiscountPolicyTest {

    @DisplayName("할인 계산 요청시")
    @Nested
    class CalculateDiscountAmount {

        @DisplayName("만원 이하의 주문인 경우 할인이 되지 않도록 반환한다.")
        @Test
        void lessThan10000() {
            // given
            final CompositeDiscountPolicy compositeDiscountPolicy =
                    new CompositeDiscountPolicy(
                            new VisitDate(25),
                            new OrderMenus(List.of(new OrderMenu(Menu.CAESAR_SALAD, 1))),
                            Giveaway.NONE);

            // when
            final DiscountDetail discountDetail = compositeDiscountPolicy.calculateDiscountAmount();

            // then
            assertThat(discountDetail.hasNoDifference()).isTrue();
        }

        @DisplayName("만원 이하의 주문인 경우 할인이 되지 않도록 반환한다.")
        @Test
        void greaterThan10000() {
            // given
            final CompositeDiscountPolicy compositeDiscountPolicy =
                    new CompositeDiscountPolicy(
                            new VisitDate(25),
                            new OrderMenus(List.of(new OrderMenu(Menu.CHRISTMAS_PASTA, 1))),
                            Giveaway.NONE);

            // when
            final DiscountDetail discountDetail = compositeDiscountPolicy.calculateDiscountAmount();

            // then
            assertThat(discountDetail.hasNoDifference()).isFalse();
        }
    }

    @DisplayName("할인 요청시 적합한 할인 금액을 반환한다.")
    @Test
    void discount() {
        // given
        final OrderMenus orderMenus =
                new OrderMenus(List.of(new OrderMenu(Menu.CHRISTMAS_PASTA, 10)));
        final CompositeDiscountPolicy compositeDiscountPolicy =
                new CompositeDiscountPolicy(new VisitDate(25), orderMenus, Giveaway.PRESENT);
        final Money beforeMoney = orderMenus.beforeDiscountPriceSum();

        // when
        final Money result = compositeDiscountPolicy.discount(beforeMoney);

        // then
        assertThat(result.toValue()).isLessThan(beforeMoney.toValue());
    }

    @DisplayName("할인 정보 조회 요청시 적합한 할인 정보를 반환하는가.")
    @Test
    void toDiscountDetails() {
        // given
        final CompositeDiscountPolicy compositeDiscountPolicy =
                new CompositeDiscountPolicy(
                        new VisitDate(25),
                        new OrderMenus(List.of(new OrderMenu(Menu.CHRISTMAS_PASTA, 1))),
                        Giveaway.NONE);

        // when
        final DiscountDetails discountDetails = compositeDiscountPolicy.toDiscountDetails();

        // then
        assertThat(discountDetails).isNotNull();
    }
}
