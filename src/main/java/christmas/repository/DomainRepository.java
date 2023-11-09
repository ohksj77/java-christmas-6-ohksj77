package christmas.repository;

import christmas.constant.ErrorMessage;
import christmas.constant.Giveaway;
import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;

import java.util.Optional;
import java.util.function.Supplier;

public class DomainRepository {

    private VisitDate visitDate;
    private OrderMenus orderMenus;
    private Giveaway giveaway;

    public void saveVisitDate(final VisitDate visitDate) {
        this.visitDate = visitDate;
    }

    public VisitDate getVisitDate() {
        return get(() -> this.visitDate);
    }

    public void saveOrderMenus(final OrderMenus orderMenus) {
        this.orderMenus = orderMenus;
    }

    public OrderMenus getOrderMenus() {
        return get(() -> this.orderMenus);
    }

    public void saveGiveaway(final Giveaway giveaway) {
        this.giveaway = giveaway;
    }

    public Giveaway getGiveaway() {
        return get(() -> this.giveaway);
    }

    private <T> T get(final Supplier<T> supplier) {
        return Optional.ofNullable(supplier.get())
                .orElseThrow(() -> new IllegalStateException(ErrorMessage.NOT_SAVED.toString()));
    }
}
