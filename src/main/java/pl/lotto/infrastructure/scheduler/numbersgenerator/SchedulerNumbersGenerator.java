package pl.lotto.infrastructure.scheduler.numbersgenerator;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numbersgenerator.LuckyNumbersGeneratorFacade;

import java.time.LocalDateTime;

@Component
public class SchedulerNumbersGenerator {

    private final LuckyNumbersGeneratorFacade luckyNumbersGeneratorFacade;
    private final NumberReceiverFacade numberReceiverFacade;

    public SchedulerNumbersGenerator(LuckyNumbersGeneratorFacade luckyNumbersGeneratorFacade, NumberReceiverFacade numberReceiverFacade) {
        this.luckyNumbersGeneratorFacade = luckyNumbersGeneratorFacade;
        this.numberReceiverFacade = numberReceiverFacade;
    }

    @Scheduled(cron = "${lotto.checker.lotteryRunOccurrence}")
    public void f() {
        System.out.println(LocalDateTime.now());
        LocalDateTime drawDate = numberReceiverFacade.specifyDrawDate().drawDate();
        luckyNumbersGeneratorFacade.generateLuckyNumbers(drawDate);
    }
}
