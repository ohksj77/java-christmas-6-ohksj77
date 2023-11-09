package christmas.io;

import christmas.domain.VisitDate;

public class InputMapper {

    public VisitDate toVisitDate(final String input) {
        return new VisitDate(Integer.parseInt(input));
    }
}
