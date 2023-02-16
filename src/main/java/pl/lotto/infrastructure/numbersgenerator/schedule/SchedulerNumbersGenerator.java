package pl.lotto.infrastructure.numbersgenerator.schedule;


import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.numbersgenerator.LuckyNumbersFacade;
import pl.lotto.infrastructure.numbersgenerator.http.DrawDateDto;
import pl.lotto.infrastructure.numbersgenerator.http.NumbersReceiverClient;

import java.time.LocalDateTime;


@Log4j2
@Component
public class SchedulerNumbersGenerator {

    private final LuckyNumbersFacade luckyNumbersFacade;
    private final NumbersReceiverClient numbersReceiverHttpClient;

    public SchedulerNumbersGenerator(LuckyNumbersFacade luckyNumbersFacade, NumbersReceiverClient numbersReceiverHttpClient) {
        this.luckyNumbersFacade = luckyNumbersFacade;
        this.numbersReceiverHttpClient = numbersReceiverHttpClient;
    }

    @Scheduled(cron = "${lotto.checker.lotteryRunOccurrence}")
    public void f() {
        DrawDateDto drawDateDto = numbersReceiverHttpClient.specifyDrawDate();
        LocalDateTime upcomingDrawDate = drawDateDto.drawDate();

//        Counter counter = new Counter();
//        System.out.println("Obecny stan countera: " + Counter.counter);
//        LocalDateTime exemplaryDate = upcomingDrawDate.plusDays(Counter.counter);
//        counter.counterIncrease();

        log.log(Level.INFO, "scheduler started");
        luckyNumbersFacade.generateLuckyNumbers(upcomingDrawDate);
    }

}
