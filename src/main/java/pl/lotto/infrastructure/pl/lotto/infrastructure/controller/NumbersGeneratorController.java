package pl.lotto.infrastructure.pl.lotto.infrastructure.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.numbersgenerator.LuckyNumbersGeneratorFacade;
import pl.lotto.numbersgenerator.dto.LuckyNumbersDto;

import javax.validation.Valid;

import java.time.LocalDateTime;


@RestController
public class NumbersGeneratorController {

    LuckyNumbersGeneratorFacade luckyNumbersGeneratorFacade;


    @GetMapping
    public ResponseEntity<LuckyNumbersDto> retrieveLuckyNumbersForDate(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return ResponseEntity.ok(luckyNumbersGeneratorFacade.retrieve(date));
    }
}
