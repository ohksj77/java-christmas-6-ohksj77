package christmas.io;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("입력값 검증 클래스")
class InputValidatorTest {

    private final InputValidator inputValidator = new InputValidator();

    @DisplayName("방문 날짜 검증시")
    @Nested
    class validateVisitDate {

        @DisplayName("숫자인 경우 예외를 던지지 않는다.")
        @Test
        void numericInput() {
            // given
            final String input = "1";

            // when
            // then
            assertThatNoException().isThrownBy(() -> inputValidator.validateVisitDate(input));
        }

        @DisplayName("숫자가 아닌 문자열이 포함된 경우 예외를 던진다.")
        @Test
        void nonNumericInput() {
            // given
            final String input = "1a";

            // when
            // then
            assertThatThrownBy(() -> inputValidator.validateVisitDate(input))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @DisplayName("주문 메뉴 입력 검증시")
    @Nested
    class ValidateOrderMenus {

        @DisplayName("입력 형식에 적합하면 예외를 던지지 않는다.")
        @Test
        void properInput() {
            // given
            final String input = "메뉴-1,두개-2";

            // when
            // then
            assertThatNoException().isThrownBy(() -> inputValidator.validateOrderMenus(input));
        }

        @DisplayName("입력 형식에 맞지 않으면 예외를 던진다.")
        @ValueSource(strings = {",메뉴-3,33-2", "12-메뉴,두개-3", "메뉴-1두개-2", "메뉴-,두개-3", "메뉴-2,두개-4,"})
        @ParameterizedTest
        void invalidInput(final String input) {
            // given
            // when
            // then
            assertThatThrownBy(() -> inputValidator.validateOrderMenus(input))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
