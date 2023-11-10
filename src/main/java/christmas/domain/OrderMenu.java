package christmas.domain;

import christmas.constant.Menu;
import christmas.constant.MenuType;
import christmas.constant.ProgressMessage;

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

    public int beforePrice() {
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
}
