package christmas.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.constant.Giveaway;
import christmas.constant.Menu;
import christmas.domain.OrderMenu;
import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;
import christmas.domain.dicountpolicy.CompositeDiscountPolicy;
import christmas.domain.dicountpolicy.DiscountPolicy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class DomainRepositoryTest {

    private final DomainRepository domainRepository = new DomainRepository();

    @DisplayName("방문날짜 저장 후 조회시 정상 조회된다.")
    @Test
    void saveAndGetVisitDate() {
        // given
        final VisitDate expected = new VisitDate(2);

        // when
        domainRepository.saveVisitDate(expected);
        final VisitDate result = domainRepository.getVisitDate();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("주문메뉴 일급컬렉션 저장 후 조회시 정상 조회된다.")
    @Test
    void saveAndGetOrderMenus() {
        // given
        final OrderMenus expected = new OrderMenus(List.of(new OrderMenu(Menu.CHOCO_CAKE, 1)));

        // when
        domainRepository.saveOrderMenus(expected);
        final OrderMenus result = domainRepository.getOrderMenus();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("증정메뉴 저장 후 조회시 정상 조회된다.")
    @Test
    void saveAndGetGiveaway() {
        // given
        final Giveaway expected = Giveaway.PRESENT;

        // when
        domainRepository.saveGiveaway(expected);
        final Giveaway result = domainRepository.getGiveaway();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("할인정책 저장 후 조회시 정상 조회된다.")
    @Test
    void saveAndGetDiscountPolicy() {
        // given
        final DiscountPolicy expected =
                new CompositeDiscountPolicy(
                        new VisitDate(24),
                        new OrderMenus(List.of(new OrderMenu(Menu.CAESAR_SALAD, 1))),
                        Giveaway.NONE);

        // when
        domainRepository.saveDiscountPolicy(expected);
        final DiscountPolicy result = domainRepository.getDiscountPolicy();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("저장 없이 조회 요청시 예외를 던진다.")
    @Test
    void getValidation() {
        // given
        // when
        // then
        assertThatThrownBy(domainRepository::getDiscountPolicy)
                .isInstanceOf(IllegalStateException.class);
    }
}
