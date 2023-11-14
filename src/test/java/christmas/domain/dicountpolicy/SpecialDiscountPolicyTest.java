package christmas.domain.dicountpolicy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.DiscountDetail;
import christmas.domain.Money;
import christmas.domain.VisitDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("스페셜 할인 정책")
class SpecialDiscountPolicyTest {

    @DisplayName("할인 금액 요청시")
    @Nested
    class CalculateDiscountAmount {

        @DisplayName("적합한 날짜인 경우 1000원의 할인 금액을 반환한다.")
        @Test
        void properDate() {
            // given
            final SpecialDiscountPolicy specialDiscountPolicy =
                    new SpecialDiscountPolicy(new VisitDate(25));

            // when
            final DiscountDetail discountDetail = specialDiscountPolicy.calculateDiscountAmount();

            // then
            final int defaultDiscountValue = 1000;
            assertThat(discountDetail.toDifferenceValue()).isEqualTo(defaultDiscountValue);
        }

        @DisplayName("적합하지 않은 날짜의 경우 0원의 할인 금액을 반환한다.")
        @Test
        void invalidDate() {
            // given
            final SpecialDiscountPolicy specialDiscountPolicy =
                    new SpecialDiscountPolicy(new VisitDate(23));

            // when
            final DiscountDetail discountDetail = specialDiscountPolicy.calculateDiscountAmount();

            // then
            assertThat(discountDetail.hasNoDifference()).isTrue();
        }
    }

    @DisplayName("할인 요청시 정상적인 할인 정보는 반환한다.")
    @Test
    void discount() {
        // given
        final SpecialDiscountPolicy specialDiscountPolicy =
                new SpecialDiscountPolicy(new VisitDate(25));

        // when
        final int amount = 3000;
        final Money result = specialDiscountPolicy.discount(new Money(amount));

        // then
        final int defaultDiscountValue = 1000;
        assertThat(result.toValue()).isEqualTo(amount - defaultDiscountValue);
    }
}
