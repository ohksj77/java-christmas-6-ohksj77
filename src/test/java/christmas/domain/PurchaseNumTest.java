package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("주문 개수 도메인")
class PurchaseNumTest {

    @DisplayName("개수 값 확인 요청시 개수를 반환한다.")
    @Test
    void toValue() {
        // given
        final int expected = 3;
        final PurchaseNum purchaseNum = new PurchaseNum(expected);

        // when
        final int result = purchaseNum.toValue();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("생성시 양수가 아닌 경우 예외를 던진다.")
    @Test
    void createValidation() {
        // given
        // when
        // then
        assertThatThrownBy(() -> new PurchaseNum(0)).isInstanceOf(IllegalArgumentException.class);
    }
}
