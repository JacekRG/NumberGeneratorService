package pl.lotto.infrastructure.pl.lotto.infrastructure.numbersgenerator;

import java.time.LocalDateTime;

public interface NumbersReceiverClient {

    LocalDateTime retrieveNextDrawDate();

}
