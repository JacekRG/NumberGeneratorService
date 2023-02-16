package pl.lotto.infrastructure.numbersgenerator.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.numbersgenerator.LuckyNumbersFacade;
import pl.lotto.numbersgenerator.dto.LuckyNumbersDto;


import java.time.LocalDateTime;


@RestController
public class NumbersGeneratorController {

    private final LuckyNumbersFacade luckyNumbersFacade;

    public NumbersGeneratorController(LuckyNumbersFacade luckyNumbersFacade) {
        this.luckyNumbersFacade = luckyNumbersFacade;
    }

    @GetMapping
    public ResponseEntity<LuckyNumbersDto> retrieveLuckyNumbersForDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        LuckyNumbersDto dto = luckyNumbersFacade.retrieveNextDrawDate(date); //facada-wyjecie z bazy
        return ResponseEntity.ok(dto);
    }
}
