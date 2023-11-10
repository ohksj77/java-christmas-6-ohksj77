package christmas.service;

import christmas.constant.Giveaway;
import christmas.domain.DiscountResults;
import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;
import christmas.domain.dicountpolicy.CompositeDiscountPolicy;
import christmas.repository.DomainRepository;

public class ChristmasService {

    private final DomainRepository domainRepository;

    public ChristmasService(final DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    public void saveVisitDate(final VisitDate visitDate) {
        domainRepository.saveVisitDate(visitDate);
    }

    public void saveOrderMenus(final OrderMenus orderMenus) {
        domainRepository.saveOrderMenus(orderMenus);
    }

    public VisitDate getVisitDate() {
        return domainRepository.getVisitDate();
    }

    public OrderMenus getOrderMenus() {
        return domainRepository.getOrderMenus();
    }

    public Giveaway calculateGiveaway() {
        final OrderMenus orderMenus = domainRepository.getOrderMenus();
        final Giveaway giveaway = orderMenus.checkGiveAway();
        domainRepository.saveGiveaway(giveaway);
        return giveaway;
    }

    public DiscountResults calculateBenefits() {
        final OrderMenus orderMenus = domainRepository.getOrderMenus();
        final VisitDate visitDate = domainRepository.getVisitDate();

        final CompositeDiscountPolicy discountPolicy =
                new CompositeDiscountPolicy(visitDate, orderMenus);
        domainRepository.save(discountPolicy);

        return discountPolicy.toDiscountResults();
    }
}
