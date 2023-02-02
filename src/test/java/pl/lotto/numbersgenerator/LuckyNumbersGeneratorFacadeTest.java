package pl.lotto.numbersgenerator;

import org.junit.jupiter.api.Test;
import pl.lotto.numbersgenerator.dto.LuckyNumbersDto;
import java.time.LocalDateTime;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

public class LuckyNumbersGeneratorFacadeTest {
    RandomNumbersGenerator randomNumbersGenerator = new RandomNumbersGenerator();

    @Test
    public void should_return_required_size_of_set() {
        //given
        LocalDateTime localDateTime = LocalDateTime.of(2022, 11, 11, 11, 11);
        LuckyNumbersGeneratorFacade numbersGenerator = new LuckyNumbersGeneratorFacade(randomNumbersGenerator);
        //when
        LuckyNumbersDto generatedNumbers = numbersGenerator.generateLuckyNumbers(localDateTime);
        //then
        assertThat(generatedNumbers.winningNumbers().size()).isEqualTo(6);
    }

    @Test
    public void should_generate_unique_numbers() {
        //given
        LocalDateTime localDateTime = LocalDateTime.of(2022, 11, 11, 11, 11);
        LuckyNumbersGeneratorFacade numbersGenerator = new LuckyNumbersGeneratorFacade(randomNumbersGenerator);
        //when
        LuckyNumbersDto generatedNumbers = numbersGenerator.generateLuckyNumbers(localDateTime);
        //then
        assertThat(new HashSet<>(generatedNumbers.winningNumbers()).size()).isEqualTo(generatedNumbers.winningNumbers().size());
    }
}
