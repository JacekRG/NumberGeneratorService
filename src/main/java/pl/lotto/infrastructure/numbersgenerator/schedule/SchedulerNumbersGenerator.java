package pl.lotto.infrastructure.numbersgenerator.schedule;


import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.infrastructure.numbersgenerator.http.NumbersReceiverHttpClientImpl;
import pl.lotto.numbersgenerator.RandomNumbersGenerator;

import java.time.LocalDateTime;


@Log4j2
@Component
public class SchedulerNumbersGenerator {

    private final NumbersReceiverHttpClientImpl numbersReceiverHttpClientImpl;
    private final RandomNumbersGenerator  randomNumbersGenerator;

    private final NumbersReceiverHttpClientImpl  numbersReceiverHttpClient;

    public SchedulerNumbersGenerator(NumbersReceiverHttpClientImpl numbersReceiverHttpClientImpl, RandomNumbersGenerator  randomNumbersGenerator, NumbersReceiverHttpClientImpl numbersReceiverHttpClient) {
        this.numbersReceiverHttpClientImpl = numbersReceiverHttpClientImpl;
        this.randomNumbersGenerator = randomNumbersGenerator;
        this.numbersReceiverHttpClient = numbersReceiverHttpClient;
    }

    @Scheduled(cron = "${lotto.checker.lotteryRunOccurrence}")
    public void f() {
        Counter counter = new Counter();
        counter.counterIncrease();
        System.out.println("Obecny stan countera: " + Counter.counter);
        String exemplaryStringDate = "2022-02-07T12:00";
        LocalDateTime exemplaryDate = LocalDateTime.parse(exemplaryStringDate).plusDays(Counter.counter);
        log.log(Level.INFO, "scheduler started");
//    randomNumbersGenerator.randomSixNumbers();
        numbersReceiverHttpClient.generateLuckyNumbers(exemplaryDate);
    }

}
