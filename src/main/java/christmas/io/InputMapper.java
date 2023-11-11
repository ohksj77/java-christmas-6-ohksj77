package christmas.io;

import christmas.constant.Menu;
import christmas.domain.OrderMenu;
import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;

import java.util.Arrays;
import java.util.stream.Collectors;

public class InputMapper {

    private static final int MENU_INDEX = 0;
    private static final int PURCHASE_NUM_INDEX = 1;
    private static final String MENUS_DELIMITER = ",";
    private static final String MENU_PRICE_DELIMITER = "-";

    public VisitDate toVisitDate(final String input) {
        return new VisitDate(Integer.parseInt(input));
    }

    public OrderMenus toOrderMenus(final String input) {
        return Arrays.stream(input.split(MENUS_DELIMITER))
                .map(this::toOrderMenu)
                .collect(Collectors.collectingAndThen(Collectors.toList(), OrderMenus::new));
    }

    private OrderMenu toOrderMenu(final String input) {
        final String[] orders = input.split(MENU_PRICE_DELIMITER);
        final Menu menu = Menu.valueOfName(orders[MENU_INDEX]);
        return new OrderMenu(menu, Integer.valueOf(orders[PURCHASE_NUM_INDEX]));
    }
}
