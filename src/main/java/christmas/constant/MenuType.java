package christmas.constant;

public enum MenuType {
    APPETIZER,
    MAIN_DISH,
    DESSERT,
    DRINK;

    public boolean isDessert() {
        return this == DESSERT;
    }

    public boolean isMainDish() {
        return this == MAIN_DISH;
    }
}
