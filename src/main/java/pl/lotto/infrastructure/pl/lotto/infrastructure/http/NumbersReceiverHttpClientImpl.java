package pl.lotto.infrastructure.pl.lotto.infrastructure.http;

import org.springframework.stereotype.Service;
import pl.lotto.infrastructure.pl.lotto.infrastructure.numbersgenerator.NumbersReceiverClient;

import java.time.LocalDateTime;

@Service
public class NumbersReceiverHttpClientImpl implements NumbersReceiverClient {


    @Override
    public LocalDateTime retrieveNextDrawDate() {
        String textDrawingDate = "2022-12-24T12:00:00";
        LocalDateTime drawingDate = LocalDateTime.parse(textDrawingDate);
        return drawingDate;
    }
}
