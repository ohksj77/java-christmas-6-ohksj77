package christmas.io;

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

    private <T> T read(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (final IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
