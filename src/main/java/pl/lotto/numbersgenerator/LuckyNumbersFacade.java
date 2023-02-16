package pl.lotto.numbersgenerator;

import org.springframework.stereotype.Component;
import pl.lotto.numbersgenerator.dto.LuckyNumbersDto;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Component
public class LuckyNumbersFacade {

    private final RandomNumbersGenerator generator;
    private final LuckyNumbersRepository luckyNumbersRepository;


    public LuckyNumbersFacade(RandomNumbersGenerator generator, LuckyNumbersRepository luckyNumbersRepository) {
        this.generator = generator;
        this.luckyNumbersRepository = luckyNumbersRepository;
    }

    public LuckyNumbersDto generateLuckyNumbers(LocalDateTime drawDate) {
        Optional<LuckyNumbersDocument> foundByDate = luckyNumbersRepository.findByDrawDate(drawDate.toString());
        if (foundByDate.isEmpty()) {
            LuckyNumbersDto numbersDto = new LuckyNumbersDto(generator.randomSixNumbers(), drawDate);
            luckyNumbersRepository.save(new LuckyNumbersDocument(numbersDto.winningNumbers(), drawDate.toString()));
            System.out.println("*********   Nie bylo tego na te date, ale wygenerowalem: " + numbersDto + "   *********");
            return numbersDto;
        }
        LuckyNumbersDocument luckyNumbersDocument = foundByDate.get();
        System.out.println("---------   Bylo juz w bazie : " + luckyNumbersDocument + " ---------");
        return new LuckyNumbersDto(luckyNumbersDocument.numbers(), drawDate);
    }

    public LuckyNumbersDto retrieveNextDrawDate(LocalDateTime drawDate) {
        System.out.println("Sprawdzam wygrane liczny na dzien : " + drawDate);
        Optional<LuckyNumbersDocument> foundByDate = luckyNumbersRepository.findByDrawDate(drawDate.toString());
        if (foundByDate.isEmpty()) {
            return new LuckyNumbersDto(emptyList(), drawDate);
        }
        luckyNumbersRepository.save(new LuckyNumbersDocument(foundByDate.get().numbers(), drawDate.toString()));
        return new LuckyNumbersDto(foundByDate.get().numbers(), drawDate);
    }
}

