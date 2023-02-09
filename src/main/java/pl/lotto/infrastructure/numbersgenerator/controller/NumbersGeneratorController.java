package pl.lotto.infrastructure.numbersgenerator.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.infrastructure.numbersgenerator.http.NumbersReceiverHttpClientImpl;
import pl.lotto.numbersgenerator.dto.LuckyNumbersDto;


import java.time.LocalDateTime;


@RestController
public class NumbersGeneratorController {

    private final NumbersReceiverHttpClientImpl numbersReceiverHttpClientImpl;

    public NumbersGeneratorController(NumbersReceiverHttpClientImpl numbersReceiverHttpClientImpl) {
        this.numbersReceiverHttpClientImpl = numbersReceiverHttpClientImpl;
    }

    @GetMapping
    public ResponseEntity<LuckyNumbersDto> retrieveLuckyNumbersForDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        LuckyNumbersDto dto = numbersReceiverHttpClientImpl.retrieveNextDrawDate(date);
        return ResponseEntity.ok(dto);
    }
}
