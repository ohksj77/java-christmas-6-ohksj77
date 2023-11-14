package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constant.Giveaway;
import christmas.constant.Menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("주문 메뉴 일급 컬렉션")
class OrderMenusTest {

    @DisplayName("할인전 총 금액 조회 요청시 계산 후 반환한다.")
    @Test
    void beforeDiscountPriceSum() {
        // given
        final OrderMenu firstOrderMenu = new OrderMenu(Menu.CHAMPAGNE, 1);
        final OrderMenu secondOrderMenu = new OrderMenu(Menu.CAESAR_SALAD, 3);
        final OrderMenus orderMenus = new OrderMenus(List.of(firstOrderMenu, secondOrderMenu));

        // when
        final Money result = orderMenus.beforeDiscountPriceSum();

        // then
        assertThat(result.toValue())
                .isEqualTo(
                        firstOrderMenu.beforeDiscountPrice()
                                + secondOrderMenu.beforeDiscountPrice());
    }

    @DisplayName("증정메뉴 확인 요청시 증정메뉴 증정 여부를 반환한다.")
    @Test
    void checkGiveAway() {
        // given
        final OrderMenu orderMenu = new OrderMenu(Menu.TAPAS, 2);
        final OrderMenus orderMenus = new OrderMenus(List.of(orderMenu));

        // when
        final Giveaway giveaway = orderMenus.checkGiveaway();

        // then
        assertThat(giveaway)
                .isEqualTo(Giveaway.valueOfPriceSum(orderMenus.beforeDiscountPriceSum()));
    }

    @DisplayName("디저트가 주문에 포함됐는지 확인 요청시")
    @Nested
    class HasDessert {

        @DisplayName("디저트가 없는 경우 false를 반환한다.")
        @Test
        void noDessert() {
            // given
            final OrderMenus orderMenus =
                    new OrderMenus(List.of(new OrderMenu(Menu.CHRISTMAS_PASTA, 3)));

            // when
            final boolean result = orderMenus.hasDessert();

            // then
            assertThat(result).isFalse();
        }

        @DisplayName("디저트가 있는 경우 true를 반환한다.")
        @Test
        void dessertExists() {
            // given
            final OrderMenus orderMenus =
                    new OrderMenus(List.of(new OrderMenu(Menu.CHOCO_CAKE, 3)));

            // when
            final boolean result = orderMenus.hasDessert();

            // then
            assertThat(result).isTrue();
        }
    }

    @DisplayName("디저트 개수 조회 요청시 갖고있는 개수를 반환한다.")
    @Test
    void dessertNum() {
        // given
        final int firstAmount = 3;
        final int secondAmount = 2;
        final OrderMenus orderMenus =
                new OrderMenus(
                        List.of(
                                new OrderMenu(Menu.CHOCO_CAKE, firstAmount),
                                new OrderMenu(Menu.ICE_CREAM, secondAmount)));

        // when
        final int result = orderMenus.dessertNum();

        // then
        assertThat(result).isEqualTo(firstAmount + secondAmount);
    }

    @DisplayName("메인 메뉴 개수 요청시 갖고 있는 개수를 반환한다.")
    @Test
    void mainDishNum() {
        // given
        final int purchaseAmount = 2;
        final OrderMenus orderMenus =
                new OrderMenus(List.of(new OrderMenu(Menu.CHRISTMAS_PASTA, purchaseAmount)));

        // when
        final int result = orderMenus.mainDishNum();

        // then
        assertThat(result).isEqualTo(purchaseAmount);
    }

    @DisplayName("메인 메뉴 포함 여부 확인 요청시")
    @Nested
    class HasMainDish {

        @DisplayName("메인 메뉴가 존재하는 경우 true를 반환한다.")
        @Test
        void mainMenuExists() {
            // given
            final OrderMenus orderMenus =
                    new OrderMenus(List.of(new OrderMenu(Menu.BARBECUE_LIB, 1)));

            // when
            final boolean result = orderMenus.hasMainDish();

            // then
            assertThat(result).isTrue();
        }

        @DisplayName("메인 메뉴가 존재하지 않는 경우 false를 반환한다.")
        @Test
        void noMainMenu() {
            // given
            final OrderMenus orderMenus = new OrderMenus(List.of(new OrderMenu(Menu.ICE_CREAM, 1)));

            // when
            final boolean result = orderMenus.hasMainDish();

            // then
            assertThat(result).isFalse();
        }
    }

    @DisplayName("할인 이전 금액 값 확인 요청시 계산후 정확한 수치를 반환한다.")
    @Test
    void toBeforePriceSumValue() {
        // given
        final OrderMenu orderMenu = new OrderMenu(Menu.TAPAS, 3);
        final OrderMenus orderMenus = new OrderMenus(List.of(orderMenu));

        // when
        final int result = orderMenus.toBeforeDiscountPriceSumValue();

        // then
        assertThat(result).isEqualTo(orderMenu.beforeDiscountPrice());
    }
}
