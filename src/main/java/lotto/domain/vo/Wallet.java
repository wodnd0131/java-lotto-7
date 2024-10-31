package lotto.domain.vo;

import java.util.List;
import java.util.stream.Stream;

import lotto.domain.entity.Lotto;

/**
 * 구매한 로또들과 구매 금액을 관리하는 불변 객체(VO)입니다.
 * 생성 이후에는 로또 목록이나 구매 금액을 수정할 수 없습니다.
 *
 * <p>내부의 로또 리스트는 생성 시점에 불변 리스트로 복사되어 저장됩니다.
 * 따라서 외부에서 전달된 원본 리스트가 변경되어도 이 객체의 상태는 영향받지 않습니다.</p>
 *
 * @param amount 로또 구매 금액
 * @param lottos 구매한 로또 목록 (불변 리스트로 저장됨)
 */

public record Wallet(PurchaseAmount amount, List<Lotto> lottos) {

    public Wallet(PurchaseAmount amount) {
        this(amount, purchaseLotto(amount));
    }

    public Wallet(PurchaseAmount amount, List<Lotto> lottos) {
        this.amount = amount;
        this.lottos = List.copyOf(lottos);
    }

    public static Wallet from(PurchaseAmount amount) {
        return new Wallet(amount);
    }

    private static List<Lotto> purchaseLotto(PurchaseAmount amount) {
        return Stream.generate(Lotto::createAutoNumbers)
            .limit(amount.calculateRemainder())
            .toList();
    }
}