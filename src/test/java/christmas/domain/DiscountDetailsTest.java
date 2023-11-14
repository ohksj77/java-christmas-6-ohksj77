package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constant.DiscountPolicyType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("할인 정보 일급 컬렉션")
class DiscountDetailsTest {

    @DisplayName("할인 금액 계산 요청시 할인 금액 총액을 반환한다.")
    @Test
    void calculateDiscountSum() {
        // given
        final int expected = 10000;
        final DiscountDetails discountDetails =
                new DiscountDetails(
                        List.of(
                                new DiscountDetail(expected, DiscountPolicyType.ALL),
                                new DiscountDetail(expected, DiscountPolicyType.WEEKDAY)));

        // when
        final DiscountDetail discountDetail = discountDetails.calculateDiscountSum();

        // then
        assertThat(discountDetail.toDifferenceValue()).isEqualTo(expected);
    }

    @DisplayName("할인 금액이 존재 여부 확인 요청시")
    @Nested
    class HasNoDiscount {

        @DisplayName("할인 결과 리스트가 비어있는 경우 true를 반환한다.")
        @Test
        void elementsEmpty() {
            // given
            final DiscountDetails discountDetails = new DiscountDetails();

            // when
            final boolean result = discountDetails.hasNoDiscount();

            // then
            assertThat(result).isTrue();
        }

        @DisplayName("전체 결과의 할인 총액이 0인 경우 true를 반환한다.")
        @Test
        void noDifference() {
            // given
            final DiscountDetails discountDetails =
                    new DiscountDetails(List.of(new DiscountDetail(0, DiscountPolicyType.ALL)));

            // when
            final boolean result = discountDetails.hasNoDiscount();

            // then
            assertThat(result).isTrue();
        }

        @DisplayName("전체 결과의 할인 총액이 0인 경우 true를 반환한다.")
        @Test
        void differenceExists() {
            // given
            final DiscountDetails discountDetails =
                    new DiscountDetails(List.of(new DiscountDetail(10000, DiscountPolicyType.ALL)));

            // when
            final boolean result = discountDetails.hasNoDiscount();

            // then
            assertThat(result).isFalse();
        }
    }

    @DisplayName("요소 리스트 조회를 요청할 경우 갖고 있는 리스트를 반환한다.")
    @Test
    void toDiscountDetails() {
        // given
        final List<DiscountDetail> expected =
                List.of(new DiscountDetail(0, DiscountPolicyType.NO_DISCOUNT));
        final DiscountDetails discountDetails = new DiscountDetails(expected);

        // when
        final List<DiscountDetail> result = discountDetails.toElements();

        // then
        assertThat(result).hasSameSizeAs(expected);
    }
}
