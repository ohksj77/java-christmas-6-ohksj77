package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constant.DiscountPolicyType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("할인 정보 도메인")
class DiscountDetailTest {

    @DisplayName("차이 액수 요청시 초기 설정 값을 반환한다.")
    @Test
    void toDifferenceValue() {
        // given
        final int expected = 3000;
        final DiscountDetail discountDetail = new DiscountDetail(expected, DiscountPolicyType.ALL);

        // when
        final int result = discountDetail.toDifferenceValue();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("할인 타입 요청시 초기 설정 값을 반환한다.")
    @Test
    void toDiscountPolicyType() {
        // given
        final DiscountPolicyType expected = DiscountPolicyType.D_DAY;
        final DiscountDetail discountDetail = new DiscountDetail(1000, expected);

        // when
        final DiscountPolicyType result = discountDetail.toDiscountPolicyType();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("차이액이 없는지 확인 요청시")
    @Nested
    class HasNoDifference {

        @DisplayName("없다면 true를 반환한다.")
        @Test
        void noDifference() {
            // given
            final DiscountDetail discountDetail = new DiscountDetail(0, DiscountPolicyType.WEEKDAY);

            // when
            final boolean result = discountDetail.hasNoDifference();

            // then
            assertThat(result).isTrue();
        }

        @DisplayName("있다면 false를 반환한다.")
        @Test
        void differenceExists() {
            // given
            final DiscountDetail discountDetail =
                    new DiscountDetail(10000, DiscountPolicyType.WEEKDAY);

            // when
            final boolean result = discountDetail.hasNoDifference();

            // then
            assertThat(result).isFalse();
        }
    }

    @DisplayName("전체 결과인지 확인 요청시")
    @Nested
    class IsAllPolicy {

        @DisplayName("맞는 경우 true를 반환한다.")
        @Test
        void all() {
            // given
            final DiscountDetail discountDetail = new DiscountDetail(1000, DiscountPolicyType.ALL);

            // when
            final boolean result = discountDetail.isAllPolicy();

            // then
            assertThat(result).isTrue();
        }

        @DisplayName("아닌 경우 false를 반환한다.")
        @Test
        void notAll() {
            // given
            final DiscountDetail discountDetail =
                    new DiscountDetail(1000, DiscountPolicyType.NO_DISCOUNT);

            // when
            final boolean result = discountDetail.isAllPolicy();

            // then
            assertThat(result).isFalse();
        }
    }
}
