package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@DisplayName("방문 일자 도메인")
class VisitDateTest {

    @DisplayName("날짜 요청시")
    @Test
    void toDate() {
        // given
        final int day = 25;
        final VisitDate visitDate = new VisitDate(day);

        // when
        final LocalDate result = visitDate.toDate();

        // then
        assertThat(result).isEqualTo(LocalDate.of(2023, 12, day));
    }

    @DisplayName("주말인지 확인 요청시")
    @Nested
    class IsWeekend {

        @DisplayName("주말인 경우 true를 반환한다.")
        @Test
        void weekend() {
            // given
            final VisitDate visitDate = new VisitDate(23);

            // when
            final boolean result = visitDate.isWeekend();

            // then
            assertThat(result).isTrue();
        }

        @DisplayName("주중인 경우 false를 반환한다.")
        @Test
        void weekday() {
            // given
            final VisitDate visitDate = new VisitDate(25);

            // when
            final boolean result = visitDate.isWeekend();

            // then
            assertThat(result).isFalse();
        }
    }

    @DisplayName("주중인지 확인 요청시")
    @Nested
    class IsWeekday {

        @DisplayName("주중인 경우 true를 반환한다.")
        @Test
        void weekend() {
            // given
            final VisitDate visitDate = new VisitDate(25);

            // when
            final boolean result = visitDate.isWeekday();

            // then
            assertThat(result).isTrue();
        }

        @DisplayName("주말인 경우 false를 반환한다.")
        @Test
        void weekday() {
            // given
            final VisitDate visitDate = new VisitDate(23);

            // when
            final boolean result = visitDate.isWeekday();

            // then
            assertThat(result).isFalse();
        }
    }

    @DisplayName("날짜 비교 요청시 날짜 차이를 숫자로 반환한다.")
    @Test
    void compareDate() {
        // given
        final VisitDate visitDate = new VisitDate(23);

        // when
        final LocalDate date = LocalDate.of(2023, 12, 24);
        final int result = visitDate.compareDate(date);

        // then
        assertThat(result).isEqualTo(LocalDate.of(2023, 12, 23).compareTo(date));
    }

    @DisplayName("생성시")
    @Nested
    class CreateValidation {

        @DisplayName("1보다 작은 숫자의 경우 예외를 던진다.")
        @Test
        void lessThanOne() {
            // given
            // when
            // then
            assertThatThrownBy(() -> new VisitDate(0)).isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("31보다 큰 숫자의 경우 예외를 던진다.")
        @Test
        void greaterThan31() {
            // given
            // when
            // then
            assertThatThrownBy(() -> new VisitDate(32))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
