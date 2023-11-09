package christmas.domain;

import christmas.constant.ErrorMessage;
import christmas.constant.MenuType;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMenus {

    private static final int MAX_ORDER_MENUS_SIZE = 20;
    private static final String NEW_LINE = "\n";
    private final List<OrderMenu> orderMenus;

    public OrderMenus(List<OrderMenu> orderMenus) {
        validate(orderMenus);
        this.orderMenus = List.copyOf(orderMenus);
    }

    private void validate(final List<OrderMenu> orderMenus) {
        if (orderMenus.isEmpty()
                || orderMenus.size() > MAX_ORDER_MENUS_SIZE
                || isDuplicate(orderMenus)
                || hasOnlyDrink(orderMenus)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_MENUS.toString());
        }
    }

    private boolean isDuplicate(final List<OrderMenu> orderMenus) {
        return orderMenus.size() != orderMenus.stream().distinct().count();
    }

    private boolean hasOnlyDrink(final List<OrderMenu> orderMenus) {
        return orderMenus.stream().filter(this::isNotDrink).findAny().isEmpty();
    }

    private boolean isNotDrink(final OrderMenu orderMenu) {
        return !orderMenu.toMenuType().equals(MenuType.DRINK);
    }

    public int beforePriceSum() {
        return orderMenus.stream().mapToInt(OrderMenu::beforePrice).sum();
    }

    @Override
    public String toString() {
        return orderMenus.stream().map(OrderMenu::toString).collect(Collectors.joining(NEW_LINE));
    }
}
