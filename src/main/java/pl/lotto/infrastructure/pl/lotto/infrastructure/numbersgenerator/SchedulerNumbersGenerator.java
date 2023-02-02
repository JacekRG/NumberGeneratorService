package pl.lotto.infrastructure.pl.lotto.infrastructure.numbersgenerator;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.numbersgenerator.LuckyNumbersGeneratorFacade;

import java.time.LocalDateTime;

@Component
public class SchedulerNumbersGenerator {

    private final LuckyNumbersGeneratorFacade luckyNumbersGeneratorFacade;

    private final NumbersReceiverClient numbersReceiverClient;

    public SchedulerNumbersGenerator(LuckyNumbersGeneratorFacade luckyNumbersGeneratorFacade, NumbersReceiverClient numbersReceiverClient) {
        this.luckyNumbersGeneratorFacade = luckyNumbersGeneratorFacade;
        this.numbersReceiverClient = numbersReceiverClient;
    }

    @Scheduled(cron = "${lotto.checker.lotteryRunOccurrence}")
    public void f() {
        System.out.println(LocalDateTime.now());
        LocalDateTime drawDate = numbersReceiverClient.retrieveNextDrawDate();
        luckyNumbersGeneratorFacade.generateLuckyNumbers(drawDate);
    }
}
