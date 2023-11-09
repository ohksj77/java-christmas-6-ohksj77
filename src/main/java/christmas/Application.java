package christmas;

import christmas.controller.ChristmasController;
import christmas.factory.ComponentFactory;

public class Application {
    public static void main(String[] args) {
        final ChristmasController christmasController =
                ComponentFactory.INSTANCE.christmasController();
        christmasController.run();
    }
}
