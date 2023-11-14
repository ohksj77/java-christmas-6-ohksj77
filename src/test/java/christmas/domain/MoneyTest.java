package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.constant.DiscountPolicyType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("금액 도메인")
class MoneyTest {

    @DisplayName("금액 요청시 갖고 있는 금액을 반환한다.")
    @Test
    void toValue() {
        // given
        final int expected = 1000;
        final Money money = new Money(expected);

        // when
        final Integer result = money.toValue();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("할인 요청시")
    @Nested
    class Discount {

        @DisplayName("할인된 금액으로 생성하여 반환한다.")
        @Test
        void minusAmountLessThanBeforeAmount() {
            // given
            final int beforeAmount = 10000;
            final Money money = new Money(beforeAmount);

            // when
            final int minusValue = 10000;
            final Money result =
                    money.discount(new DiscountDetail(minusValue, DiscountPolicyType.ALL));

            // then
            assertThat(result.toValue()).isEqualTo(beforeAmount - minusValue);
        }

        @DisplayName("할인된 금액으로 생성하여 반환한다.")
        @Test
        void minusAmountGreaterThanBeforeAmount() {
            // given
            final int beforeAmount = 10000;
            final Money money = new Money(beforeAmount);

            // when
            // then
            final int minusValue = 10001;
            assertThatThrownBy(
                            () ->
                                    money.discount(
                                            new DiscountDetail(minusValue, DiscountPolicyType.ALL)))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @DisplayName("생성시 음수인 경우 예외를 던진다.")
    @Test
    void createValidation() {
        // given
        // when
        // then
        assertThatThrownBy(() -> new Money(-1)).isInstanceOf(IllegalArgumentException.class);
    }
}
