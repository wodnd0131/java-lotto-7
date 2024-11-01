package lotto.domain.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class LottoTest {
    @Test
    @DisplayName("로또 번호가 6개가 아닌 경우 예외가 발생한다.")
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호에 중복된 숫자가 있는 경우 예외가 발생한다.")
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호들을 자동으로 생성할 수 있다.")
    void lottoNumbersShouldGenerateItself() {
        Lotto lotto = Lotto.createAutoNumbers();
        String lottoString = lotto.toString();
        assertThat(lottoString)
            .startsWith("[")
            .endsWith("]")
            .contains(", ")
            .matches("\\[\\d+(?:,\\s\\d+){5}\\]");
    }

    @Test
    @DisplayName("로또 번호는 자동으로 오름차순 정렬된다")
    void lottoNumbersShouldBeSortedItself() {
        Lotto lotto = new Lotto(List.of(6, 5, 4, 3, 2, 1));

        assertThat(lotto.toString())
            .isEqualTo("[1, 2, 3, 4, 5, 6]");
    }

}