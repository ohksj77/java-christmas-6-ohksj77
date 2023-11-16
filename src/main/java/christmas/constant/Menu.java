package christmas.constant;

import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6_000, MenuType.APPETIZER),
    TAPAS("타파스", 5_500, MenuType.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, MenuType.APPETIZER),
    T_BONE_STEAK("티본스테이크", 55_000, MenuType.MAIN_DISH),
    BARBECUE_LIB("바비큐립", 54_000, MenuType.MAIN_DISH),
    SEAFOOD_PASTA("해산물파스타", 35_000, MenuType.MAIN_DISH),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MenuType.MAIN_DISH),
    CHOCO_CAKE("초코케이크", 15_000, MenuType.DESSERT),
    ICE_CREAM("아이스크림", 5_000, MenuType.DESSERT),
    ZERO_COLA("제로콜라", 3_000, MenuType.DRINK),
    RED_WINE("레드와인", 60_000, MenuType.DRINK),
    CHAMPAGNE("샴페인", 25_000, MenuType.DRINK);

    private final String name;
    private final int price;
    private final MenuType menuType;

    Menu(final String name, final int price, final MenuType menuType) {
        this.name = name;
        this.price = price;
        this.menuType = menuType;
    }

    public static Menu valueOfName(final String name) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(name))
                .findAny()
                .orElseThrow(
                        () ->
                                new IllegalArgumentException(
                                        ErrorMessage.INVALID_ORDER_MENUS.toString()));
    }

    public MenuType toMenuType() {
        return this.menuType;
    }

    public String toName() {
        return this.name;
    }

    public int toPrice() {
        return this.price;
    }
}
