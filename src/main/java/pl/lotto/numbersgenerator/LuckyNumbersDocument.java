package pl.lotto.numbersgenerator;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;
import java.util.Collection;

@Document
public record LuckyNumbersDocument(

        Collection<Integer> numbers,
        @Id
        String drawDate
) {
}
