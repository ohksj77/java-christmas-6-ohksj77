package christmas.domain;

import christmas.constant.Menu;
import christmas.constant.MenuType;
import christmas.constant.ProgressMessage;

public class OrderMenu {

    private final Menu menu;
    private final Integer amount;

    public OrderMenu(final Menu menu, final Integer amount) {
        this.menu = menu;
        this.amount = amount;
    }

    public MenuType toMenuType() {
        return menu.toMenuType();
    }

    @Override
    public String toString() {
        return String.format(
                ProgressMessage.ORDERED_MENUS_DETAILS.toString(), menu.toName(), amount);
    }
}
