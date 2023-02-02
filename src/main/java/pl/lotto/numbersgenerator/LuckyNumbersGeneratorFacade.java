package pl.lotto.numbersgenerator;


import org.springframework.stereotype.Component;
import pl.lotto.numbersgenerator.dto.LuckyNumbersDto;

import java.time.LocalDateTime;

import static java.util.Collections.emptyList;

@Component
public class LuckyNumbersGeneratorFacade {

    LocalDateTime drawDate;

    private final SixNumberGenerable generator;

    public LuckyNumbersGeneratorFacade(SixNumberGenerable generator) {
        this.generator = generator;
    }

    public LuckyNumbersDto generateLuckyNumbers(LocalDateTime drawDate) {
        if (generator.randomSixNumbers().isEmpty()) {
            return new LuckyNumbersDto(generator.randomSixNumbers(), drawDate);
        }
        return new LuckyNumbersDto(generator.randomSixNumbers(), drawDate);
    }

    public LuckyNumbersDto retrieve(LocalDateTime drawDate){
        if (generator.randomSixNumbers().isEmpty()) {
            return new LuckyNumbersDto(emptyList(), drawDate);
        }
        return new LuckyNumbersDto(generator.randomSixNumbers(), drawDate);
    }
}
