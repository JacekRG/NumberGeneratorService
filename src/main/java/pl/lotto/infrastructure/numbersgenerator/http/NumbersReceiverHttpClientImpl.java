package pl.lotto.infrastructure.numbersgenerator.http;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.lotto.numbersgenerator.LuckyNumbersDocument;
import pl.lotto.numbersgenerator.LuckyNumbersRepository;
import pl.lotto.numbersgenerator.RandomNumbersGenerator;
import pl.lotto.numbersgenerator.dto.LuckyNumbersDto;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class NumbersReceiverHttpClientImpl implements NumbersReceiverClient {

    private final RestTemplate restTemplate;
    private final RandomNumbersGenerator generator;
    private final LuckyNumbersRepository luckyNumbersRepository;

//    @Value("${resultCheckerFacade.url:localhost}")
//    private String resultCheckerFacadeUrl;
//
//    @Value("${resultCheckerFacade.port:8088}")
//    private String resultCheckerFacadePort;

    public NumbersReceiverHttpClientImpl(RestTemplate restTemplate, RandomNumbersGenerator generator, LuckyNumbersRepository luckyNumbersRepository) {
        this.restTemplate = restTemplate;
        this.generator = generator;
        this.luckyNumbersRepository = luckyNumbersRepository;
    }


    @Override
    public LuckyNumbersDto generateLuckyNumbers(LocalDateTime drawDate) {
        Optional<LuckyNumbersDocument> foundByDate = luckyNumbersRepository.findByDrawDate(drawDate);
        if (foundByDate.isEmpty()) {
            LuckyNumbersDto numbersDto = new LuckyNumbersDto(generator.randomSixNumbers(), drawDate);
            luckyNumbersRepository.save(new LuckyNumbersDocument(numbersDto.winningNumbers(), drawDate));
            return numbersDto;
        }
        LuckyNumbersDocument luckyNumbersDocument = foundByDate.get();
        return new LuckyNumbersDto(luckyNumbersDocument.numbers(), luckyNumbersDocument.drawDate());
    }

    @Override
    public LuckyNumbersDto retrieveNextDrawDate(LocalDateTime drawDate) {
        Optional<LuckyNumbersDocument> foundByDate = luckyNumbersRepository.findByDrawDate(drawDate);
        if (foundByDate.isEmpty()) {
            return new LuckyNumbersDto(emptyList(), drawDate);
        }
        return new LuckyNumbersDto(foundByDate.get().numbers(), foundByDate.get().drawDate());
    }
//    @Override
//    public LuckyNumbersDto retrieveNextDrawDate(LocalDateTime drawDate) {
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            ObjectMapper objectMapper = new ObjectMapper();
//            HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(drawDate), headers);
//            ResponseEntity<LuckyNumbersDto> response = restTemplate.postForEntity("http://localhost:8088", request, LuckyNumbersDto.class);
//            return response.getBody();
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("Error while retrieving next draw date", e);
//        }
//    }
}
