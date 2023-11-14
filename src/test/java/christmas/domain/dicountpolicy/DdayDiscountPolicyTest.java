package christmas.domain.dicountpolicy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.DiscountDetail;
import christmas.domain.Money;
import christmas.domain.VisitDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("크리스마스 디데이 할인 정책")
class DdayDiscountPolicyTest {

    @DisplayName("할인 금액 요청시")
    @Nested
    class CalculateDiscountAmount {

        @DisplayName("크리스마스의 경우 최대 할인 금액을 반환하는가")
        @Test
        void dday() {
            // given
            final DdayDiscountPolicy ddayDiscountPolicy = new DdayDiscountPolicy(new VisitDate(25));

            // when
            final DiscountDetail discountDetail = ddayDiscountPolicy.calculateDiscountAmount();

            // then
            assertThat(discountDetail.toDifferenceValue()).isEqualTo(3400);
        }

        @DisplayName("12월 1일인 경우 최소 할인 금액을 반환하는가")
        @Test
        void december1st() {
            // given
            final DdayDiscountPolicy ddayDiscountPolicy = new DdayDiscountPolicy(new VisitDate(1));

            // when
            final DiscountDetail discountDetail = ddayDiscountPolicy.calculateDiscountAmount();

            // then
            assertThat(discountDetail.toDifferenceValue()).isEqualTo(1000);
        }

        @DisplayName("크리스마스 다음날의 경우 0원을 반환하는가")
        @Test
        void afterDday() {
            // given
            final DdayDiscountPolicy ddayDiscountPolicy = new DdayDiscountPolicy(new VisitDate(26));

            // when
            final DiscountDetail discountDetail = ddayDiscountPolicy.calculateDiscountAmount();

            // then
            assertThat(discountDetail.hasNoDifference()).isTrue();
        }
    }

    @DisplayName("할인 요청시 정상적인 할인 정보는 반환하는가")
    @Test
    void discount() {
        // given
        final int date = 2;
        final DdayDiscountPolicy ddayDiscountPolicy = new DdayDiscountPolicy(new VisitDate(date));

        // when
        final int amount = 1500;
        final Money result = ddayDiscountPolicy.discount(new Money(amount));

        // then
        assertThat(result.toValue()).isEqualTo(amount - 1000 - (date - 1) * 100);
    }
}
