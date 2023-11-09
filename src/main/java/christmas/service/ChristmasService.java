package christmas.service;

import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;
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
}
