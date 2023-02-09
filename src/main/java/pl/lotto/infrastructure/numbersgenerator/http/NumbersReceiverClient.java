package pl.lotto.infrastructure.numbersgenerator.http;

import pl.lotto.numbersgenerator.dto.LuckyNumbersDto;

import java.time.LocalDateTime;

public interface NumbersReceiverClient {

    LuckyNumbersDto generateLuckyNumbers(LocalDateTime drawDate);
    LuckyNumbersDto retrieveNextDrawDate(LocalDateTime drawDate);

}
