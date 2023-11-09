package christmas.domain;

import christmas.constant.Menu;
import christmas.constant.MenuType;

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
}
