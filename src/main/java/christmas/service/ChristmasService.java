package christmas.service;

import christmas.constant.EventBadge;
import christmas.constant.Giveaway;
import christmas.domain.DiscountDetail;
import christmas.domain.DiscountDetails;
import christmas.domain.Money;
import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;
import christmas.domain.dicountpolicy.CompositeDiscountPolicy;
import christmas.domain.dicountpolicy.DiscountPolicy;
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

    public DiscountDetails calculateBenefits() {
        final OrderMenus orderMenus = domainRepository.getOrderMenus();
        final VisitDate visitDate = domainRepository.getVisitDate();
        final Giveaway giveaway = domainRepository.getGiveaway();

        final CompositeDiscountPolicy discountPolicy =
                new CompositeDiscountPolicy(visitDate, orderMenus, giveaway);
        domainRepository.save(discountPolicy);

        return discountPolicy.toDiscountDetails();
    }

    public DiscountDetail calculateDiscountSum() {
        final DiscountPolicy discountPolicy = domainRepository.getDiscountPolicy();
        return discountPolicy.calculateDiscountAmount();
    }

    public Money checkExpectedAmount() {
        final DiscountPolicy discountPolicy = domainRepository.getDiscountPolicy();
        final OrderMenus orderMenus = domainRepository.getOrderMenus();

        return discountPolicy.discount(orderMenus.beforePriceSum());
    }

    public EventBadge findEventBadge() {
        final DiscountDetail discountDetail = calculateDiscountSum();
        return EventBadge.valueOfAmount(discountDetail.toDifferenceValue());
    }
}
