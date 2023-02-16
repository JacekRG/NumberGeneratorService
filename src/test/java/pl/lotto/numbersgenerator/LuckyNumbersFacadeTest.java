package pl.lotto.numbersgenerator;

import org.junit.jupiter.api.Test;
import pl.lotto.numbersgenerator.dto.LuckyNumbersDto;
import java.time.LocalDateTime;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class LuckyNumbersFacadeTest {
    RandomNumbersGenerator randomNumbersGenerator = new RandomNumbersGenerator();

    @Test
    public void should_return_required_size_of_set() {
        //given
        LocalDateTime localDateTime = LocalDateTime.of(2022, 11, 11, 11, 11);
        LuckyNumbersRepository luckyNumbersRepository = mock(LuckyNumbersRepository.class);
        LuckyNumbersFacade luckyNumbersFacade = new LuckyNumbersFacade(randomNumbersGenerator, luckyNumbersRepository);
        //when
        LuckyNumbersDto generatedNumbers = luckyNumbersFacade.generateLuckyNumbers(localDateTime);
        //then
        assertThat(generatedNumbers.winningNumbers().size()).isEqualTo(6);
    }

    @Test
    public void should_generate_unique_numbers() {
        //given
        LocalDateTime localDateTime = LocalDateTime.of(2022, 11, 11, 11, 11);
        LuckyNumbersRepository luckyNumbersRepository = mock(LuckyNumbersRepository.class);
        LuckyNumbersFacade luckyNumbersFacade = new LuckyNumbersFacade(randomNumbersGenerator, luckyNumbersRepository);
        //when
        LuckyNumbersDto generatedNumbers = luckyNumbersFacade.generateLuckyNumbers(localDateTime);
        //then
        assertThat(new HashSet<>(generatedNumbers.winningNumbers()).size()).isEqualTo(generatedNumbers.winningNumbers().size());
    }
}
