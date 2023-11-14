package christmas.domain;

import christmas.constant.Menu;
import christmas.constant.MenuType;
import christmas.constant.ProgressMessage;

import java.util.Objects;
import java.util.function.Predicate;

public final class OrderMenu {

    private final Menu menu;
    private final PurchaseNum purchaseNum;

    public OrderMenu(final Menu menu, final Integer purchaseAmount) {
        this.menu = menu;
        this.purchaseNum = new PurchaseNum(purchaseAmount);
    }

    public MenuType toMenuType() {
        return menu.toMenuType();
    }

    public int beforeDiscountPrice() {
        return menu.toPrice() * purchaseNum.toValue();
    }

    @Override
    public String toString() {
        return String.format(
                ProgressMessage.ORDERED_MENUS_DETAILS.toString(), menu.toName(), purchaseNum);
    }

    public boolean isDessert() {
        return isMenuType(MenuType::isDessert);
    }

    public boolean isMainDish() {
        return isMenuType(MenuType::isMainDish);
    }

    private boolean isMenuType(final Predicate<MenuType> isMenuType) {
        final MenuType menuType = menu.toMenuType();
        return isMenuType.test(menuType);
    }

    public int toPurchaseNumValue() {
        return this.purchaseNum.toValue();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final OrderMenu orderMenu = (OrderMenu) o;
        return menu == orderMenu.menu;
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu);
    }
}
