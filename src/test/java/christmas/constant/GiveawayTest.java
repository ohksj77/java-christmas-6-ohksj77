package christmas.constant;

import christmas.domain.Money;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("증정 열거형")
class GiveawayTest {

    @DisplayName("가격별 증정 여부 요청시")
    @Nested
    class ValueOfPriceSum {

        @DisplayName("12만원 이상인 경우 증정 메뉴를 반환한다.")
        @Test
        void present() {
            // given
            final Money money = new Money(120000);

            // when
            final Giveaway giveaway = Giveaway.valueOfPriceSum(money);

            // then
            Assertions.assertThat(giveaway).isEqualTo(Giveaway.PRESENT);
        }

        @DisplayName("12만원보다 적은 경우  NONE을 반환한다.")
        @Test
        void none() {
            // given
            final Money money = new Money(20000);

            // when
            final Giveaway giveaway = Giveaway.valueOfPriceSum(money);

            // then
            Assertions.assertThat(giveaway).isEqualTo(Giveaway.NONE);
        }
    }
}
