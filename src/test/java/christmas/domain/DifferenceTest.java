package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("차액 도메인")
class DifferenceTest {

    @DisplayName("차액이 없는지 확인 요청시")
    @Nested
    class HasNoValue {

        @DisplayName("차액이 없으면 true를 반환한다.")
        @Test
        void noValue() {
            // given
            final Difference difference = new Difference(0);

            // when
            final boolean result = difference.hasNoValue();

            // then
            assertThat(result).isTrue();
        }

        @DisplayName("차액이 있으면 false를 반환한다.")
        @Test
        void valueExists() {
            // given
            final Difference difference = new Difference(45000);

            // when
            final boolean result = difference.hasNoValue();

            // then
            assertThat(result).isFalse();
        }
    }

    @DisplayName("생성시")
    @Test
    void createValidation() {
        // given
        // when
        // then
        assertThatThrownBy(() -> new Difference(-1)).isInstanceOf(IllegalStateException.class);
    }
}
