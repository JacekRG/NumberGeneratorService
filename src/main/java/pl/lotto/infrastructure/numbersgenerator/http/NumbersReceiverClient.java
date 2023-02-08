package pl.lotto.infrastructure.numbersgenerator.http;

import java.time.LocalDateTime;

public interface NumbersReceiverClient {

    LocalDateTime retrieveNextDrawDate();

}
