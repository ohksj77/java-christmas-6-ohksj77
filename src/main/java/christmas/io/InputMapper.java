package christmas.io;

import christmas.constant.Menu;
import christmas.domain.OrderMenu;
import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;

import java.util.Arrays;
import java.util.stream.Collectors;

public class InputMapper {

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

    private OrderMenu toOrderMenu(final String string) {
        final String[] strings = string.split(MENU_PRICE_DELIMITER);
        final Menu menu = Menu.valueOfName(strings[0]);
        return new OrderMenu(menu, Integer.valueOf(strings[1]));
    }
}
