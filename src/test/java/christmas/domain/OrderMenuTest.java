package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constant.Menu;
import christmas.constant.MenuType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("주문 메뉴 도메인")
class OrderMenuTest {

    @DisplayName("메뉴의 타입 요청시 갖고 있는 메뉴의 타입을 반환한다.")
    @Test
    void toMenuType() {
        // given
        final Menu champagne = Menu.CHAMPAGNE;
        final OrderMenu orderMenu = new OrderMenu(champagne, 3);

        // when
        final MenuType result = orderMenu.toMenuType();

        // then
        assertThat(result).isEqualTo(champagne.toMenuType());
    }

    @DisplayName("할인 전 금액 요청시 메뉴의 금액과 개수를 통해 계산해 반환한다.")
    @Test
    void beforeDiscountPrice() {
        // given
        final int purchaseAmount = 2;
        final Menu caesarSalad = Menu.CAESAR_SALAD;
        final OrderMenu orderMenu = new OrderMenu(caesarSalad, purchaseAmount);

        // when
        final int result = orderMenu.beforeDiscountPrice();

        // then
        assertThat(result).isEqualTo(caesarSalad.toPrice() * purchaseAmount);
    }

    @DisplayName("디저트인지 확인 요청시")
    @Nested
    class IsDessert {

        @DisplayName("디저트가 맞는 경우 true를 반환한다.")
        @Test
        void dessert() {
            // given
            final OrderMenu orderMenu = new OrderMenu(Menu.CHOCO_CAKE, 10);

            // when
            final boolean result = orderMenu.isDessert();

            // then
            assertThat(result).isTrue();
        }

        @DisplayName("디저트가 맞는 경우 false를 반환한다.")
        @Test
        void notDessert() {
            // given
            final OrderMenu orderMenu = new OrderMenu(Menu.BARBECUE_LIB, 10);

            // when
            final boolean result = orderMenu.isDessert();

            // then
            assertThat(result).isFalse();
        }
    }

    @DisplayName("디저트인지 확인 요청시")
    @Nested
    class IsMainDish {

        @DisplayName("디저트가 맞는 경우 true를 반환한다.")
        @Test
        void mainDish() {
            // given
            final OrderMenu orderMenu = new OrderMenu(Menu.BARBECUE_LIB, 10);

            // when
            final boolean result = orderMenu.isMainDish();

            // then
            assertThat(result).isTrue();
        }

        @DisplayName("디저트가 맞는 경우 false를 반환한다.")
        @Test
        void notDessert() {
            // given
            final OrderMenu orderMenu = new OrderMenu(Menu.CHOCO_CAKE, 10);

            // when
            final boolean result = orderMenu.isMainDish();

            // then
            assertThat(result).isFalse();
        }
    }

    @DisplayName("구매 개수 요청시 갖고 있는 구매 개수를 반환한다.")
    @Test
    void toPurchaseNumValue() {
        // given
        final int expected = 7;
        final OrderMenu orderMenu = new OrderMenu(Menu.CHRISTMAS_PASTA, expected);

        // when
        final int result = orderMenu.toPurchaseNumValue();

        // then
        assertThat(result).isEqualTo(expected);
    }
}
