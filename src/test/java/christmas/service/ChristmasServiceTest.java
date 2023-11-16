package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constant.EventBadge;
import christmas.constant.Giveaway;
import christmas.constant.Menu;
import christmas.domain.DiscountDetail;
import christmas.domain.DiscountDetails;
import christmas.domain.Money;
import christmas.domain.OrderMenu;
import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;
import christmas.domain.dicountpolicy.CompositeDiscountPolicy;
import christmas.repository.DomainRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("크리스마스 서비스")
class ChristmasServiceTest {

    private final DomainRepository domainRepository = new DomainRepository();
    private final ChristmasService christmasService = new ChristmasService(domainRepository);

    @DisplayName("방문 날짜 저장 요청시 정상 저장된다.")
    @Test
    void saveVisitDate() {
        // given
        // when
        christmasService.saveVisitDate(new VisitDate(1));

        // then
        assertThat(domainRepository.getVisitDate()).isNotNull();
    }

    @DisplayName("주문 메뉴 저장 요청시 정상 저장된다.")
    @Test
    void saveOrderMenus() {
        // given

        // when
        christmasService.saveOrderMenus(
                new OrderMenus(List.of(new OrderMenu(Menu.CHRISTMAS_PASTA, 1))));

        // then
        assertThat(domainRepository.getOrderMenus()).isNotNull();
    }

    @DisplayName("방문 날짜 조회 요청시 정상 조회된다.")
    @Test
    void getVisitDate() {
        // given
        final VisitDate expected = new VisitDate(1);
        domainRepository.saveVisitDate(expected);

        // when
        final VisitDate result = christmasService.getVisitDate();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("주문 메뉴 조회 요청시 정상 조회된다.")
    @Test
    void getOrderMenus() {
        // given
        final OrderMenus expected = new OrderMenus(List.of(new OrderMenu(Menu.CHRISTMAS_PASTA, 2)));
        domainRepository.saveOrderMenus(expected);

        // when
        final OrderMenus result = christmasService.getOrderMenus();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("증정메뉴 확인 요청시 정상 반환한다.")
    @Test
    void calculateGiveaway() {
        // given
        final OrderMenus orderMenus =
                new OrderMenus(List.of(new OrderMenu(Menu.CHRISTMAS_PASTA, 20)));
        domainRepository.saveOrderMenus(orderMenus);

        // when
        final Giveaway result = christmasService.calculateGiveaway();

        // then
        assertThat(result).isEqualTo(orderMenus.checkGiveaway());
    }

    @DisplayName("혜택 계산 요청시 혜택 정보를 반환한다.")
    @Test
    void calculateBenefits() {
        // given
        domainRepository.saveOrderMenus(
                new OrderMenus(List.of(new OrderMenu(Menu.CHRISTMAS_PASTA, 20))));
        domainRepository.saveGiveaway(Giveaway.PRESENT);
        domainRepository.saveVisitDate(new VisitDate(1));

        // when
        final DiscountDetails result = christmasService.calculateBenefits();

        // then
        assertThat(result).isNotNull();
    }

    @DisplayName("혜택 총합 요청시 계산 후 반환한다.")
    @Test
    void calculateDiscountSum() {
        // given
        domainRepository.saveDiscountPolicy(
                new CompositeDiscountPolicy(
                        new VisitDate(23),
                        new OrderMenus(List.of(new OrderMenu(Menu.CHRISTMAS_PASTA, 6))),
                        Giveaway.PRESENT));

        // when
        final DiscountDetail result = christmasService.calculateDiscountSum();

        // then
        assertThat(result).isNotNull();
    }

    @DisplayName("예상 결제 금액 요청시 계산 후 반환한다.")
    @Test
    void checkExpectedAmount() {
        // given
        final OrderMenus orderMenus =
                new OrderMenus(List.of(new OrderMenu(Menu.CHRISTMAS_PASTA, 20)));
        domainRepository.saveOrderMenus(orderMenus);

        domainRepository.saveDiscountPolicy(
                new CompositeDiscountPolicy(new VisitDate(23), orderMenus, Giveaway.PRESENT));

        // when
        final Money result = christmasService.checkExpectedAmount();

        // then
        assertThat(result).isNotNull();
    }

    @DisplayName("이벤트 뱃지 확인 요청시 계산 후 반환한다.")
    @Test
    void findEventBadge() {
        // given
        domainRepository.saveDiscountPolicy(
                new CompositeDiscountPolicy(
                        new VisitDate(23),
                        new OrderMenus(List.of(new OrderMenu(Menu.CHRISTMAS_PASTA, 6))),
                        Giveaway.PRESENT));

        // when
        final EventBadge result = christmasService.findEventBadge();

        // then
        assertThat(result).isNotNull();
    }
}
