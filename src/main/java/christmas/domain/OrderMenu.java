package christmas.domain;

import christmas.constant.Menu;
import christmas.constant.MenuType;
import christmas.constant.ProgressMessage;

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
}
