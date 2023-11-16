package christmas.io;

import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;

import java.util.function.Supplier;

public class InputManager {

    private final InputView inputView;
    private final InputMapper inputMapper;

    public InputManager(final InputView inputView, final InputMapper inputMapper) {
        this.inputView = inputView;
        this.inputMapper = inputMapper;
    }

    public VisitDate readVisitDate() {
        return read(
                () -> {
                    final String input = inputView.readVisitDate();
                    return inputMapper.toVisitDate(input);
                });
    }

    public OrderMenus readOrderMenus() {
        return read(
                () -> {
                    final String input = inputView.readOrderMenus();
                    return inputMapper.toOrderMenus(input);
                });
    }

    private <T> T read(final Supplier<T> readInput) {
        while (true) {
            try {
                return readInput.get();
            } catch (final IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
