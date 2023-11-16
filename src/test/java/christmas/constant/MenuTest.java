package christmas.constant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("메뉴 열거형")
class MenuTest {

    @DisplayName("이름에 따른 메뉴 요청시")
    @Nested
    class ValueOfName {

        @DisplayName("존재하는 메뉴의 경우 해당 메뉴를 반환한다.")
        @Test
        void existingMenu() {
            // given
            final Menu expected = Menu.BARBECUE_LIB;

            // when
            final Menu result = Menu.valueOfName(expected.toName());

            // then
            assertThat(result).isEqualTo(expected);
        }

        @DisplayName("없는 메뉴 이름의 경우 예외를 던진다.")
        @Test
        void notExistingMenu() {
            // given
            // when
            // then
            assertThatThrownBy(() -> Menu.valueOfName("없는메뉴"))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
