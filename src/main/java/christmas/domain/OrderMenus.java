package christmas.domain;

import christmas.constant.ErrorMessage;
import christmas.constant.Giveaway;
import christmas.constant.MenuType;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class OrderMenus {

    private static final int MAX_ORDER_MENUS_SIZE = 20;
    private static final String NEW_LINE = "\n";
    private final List<OrderMenu> elements;

    public OrderMenus(final List<OrderMenu> elements) {
        validate(elements);
        this.elements = List.copyOf(elements);
    }

    private void validate(final List<OrderMenu> orderMenus) {
        if (orderMenus.isEmpty()
                || hasMoreThanMaxSize(orderMenus)
                || isDuplicate(orderMenus)
                || hasOnlyDrink(orderMenus)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_MENUS.toString());
        }
    }

    private boolean hasMoreThanMaxSize(final List<OrderMenu> orderMenus) {
        return orderMenus.stream().mapToInt(OrderMenu::toPurchaseNumValue).sum()
                > MAX_ORDER_MENUS_SIZE;
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

    public Money beforeDiscountPriceSum() {
        final int sum = elements.stream().mapToInt(OrderMenu::beforeDiscountPrice).sum();
        return new Money(sum);
    }

    public Giveaway checkGiveaway() {
        return Giveaway.valueOfPriceSum(beforeDiscountPriceSum());
    }

    public boolean hasDessert() {
        return hasMenuType(OrderMenu::isDessert);
    }

    public int dessertNum() {
        return menuTypeNum(OrderMenu::isDessert);
    }

    private boolean hasMenuType(final Predicate<OrderMenu> hasMenuType) {
        return this.elements.stream().anyMatch(hasMenuType);
    }

    public int mainDishNum() {
        return menuTypeNum(OrderMenu::isMainDish);
    }

    private int menuTypeNum(final Predicate<OrderMenu> isMenuType) {
        return this.elements.stream()
                .filter(isMenuType)
                .mapToInt(OrderMenu::toPurchaseNumValue)
                .sum();
    }

    public boolean hasMainDish() {
        return hasMenuType(OrderMenu::isMainDish);
    }

    public int toBeforeDiscountPriceSumValue() {
        return beforeDiscountPriceSum().toValue();
    }

    @Override
    public String toString() {
        return elements.stream().map(OrderMenu::toString).collect(Collectors.joining(NEW_LINE));
    }
}
