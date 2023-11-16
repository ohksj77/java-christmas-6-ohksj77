package christmas.constant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("이벤트 뱃지 열거형")
class EventBadgeTest {

    @DisplayName("금액에 맞는 뱃지 요청시")
    @Nested
    class ValueOfAmount {

        @DisplayName("3000의 경우 NONE를 반환한다.")
        @Test
        void noneWhenAmountIs3000() {
            // given
            final int amount = 3000;

            // when
            final EventBadge eventBadge = EventBadge.valueOfAmount(amount);

            // then
            assertThat(eventBadge).isEqualTo(EventBadge.NONE);
        }

        @DisplayName("5000의 경우 STAR를 반환한다.")
        @Test
        void starWhenAmountIs5000() {
            // given
            final int amount = 5000;

            // when
            final EventBadge eventBadge = EventBadge.valueOfAmount(amount);

            // then
            assertThat(eventBadge).isEqualTo(EventBadge.STAR);
        }

        @DisplayName("15000원의 경우 TREE를 반환한다.")
        @Test
        void treeWhenAmountIs15000() {
            // given
            final int amount = 15000;

            // when
            final EventBadge eventBadge = EventBadge.valueOfAmount(amount);

            // then
            assertThat(eventBadge).isEqualTo(EventBadge.TREE);
        }

        @DisplayName("20000원의 경우 SANTA를 반환한다.")
        @Test
        void santaWhenAmountIs20000() {
            // given
            final int amount = 20000;

            // when
            final EventBadge eventBadge = EventBadge.valueOfAmount(amount);

            // then
            assertThat(eventBadge).isEqualTo(EventBadge.SANTA);
        }
    }
}
