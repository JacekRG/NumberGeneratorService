package pl.lotto.infrastructure.numbersgenerator.schedule;


import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.infrastructure.numbersgenerator.http.NumbersReceiverClient;
import pl.lotto.numbersgenerator.LuckyNumbersGeneratorFacade;

import java.time.LocalDateTime;

@Log4j2
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

        log.log(Level.INFO, "scheduler started");

        System.out.println(LocalDateTime.now());
        LocalDateTime drawDate = numbersReceiverClient.retrieveNextDrawDate();
        luckyNumbersGeneratorFacade.generateLuckyNumbers(drawDate);
    }
}
