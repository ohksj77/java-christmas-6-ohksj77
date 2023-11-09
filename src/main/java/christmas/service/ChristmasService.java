package christmas.service;

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
}
