package christmas.repository;

import christmas.constant.ErrorMessage;
import christmas.domain.VisitDate;

import java.util.Optional;
import java.util.function.Supplier;

public class DomainRepository {

    private VisitDate visitDate;

    public void saveVisitDate(final VisitDate visitDate) {
        this.visitDate = visitDate;
    }

    public VisitDate getVisitDate() {
        return get(() -> this.visitDate);
    }

    private <T> T get(final Supplier<T> supplier) {
        return Optional.ofNullable(supplier.get())
                .orElseThrow(() -> new IllegalStateException(ErrorMessage.NOT_SAVED.toString()));
    }
}
