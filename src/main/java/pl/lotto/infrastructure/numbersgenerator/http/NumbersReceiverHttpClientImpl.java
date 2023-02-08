package pl.lotto.infrastructure.numbersgenerator.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class NumbersReceiverHttpClientImpl implements NumbersReceiverClient {

    private final RestTemplate restTemplate;

    @Value("${resultCheckerFacade.url:localhost}")
    private String resultCheckerFacadeUrl;

    @Value("${resultCheckerFacade.port:8088}")
    private String resultCheckerFacadePort;

    public NumbersReceiverHttpClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public LocalDateTime retrieveNextDrawDate() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String textDrawingDate = "2022-12-24T12:00:00";
        LocalDateTime drawingDate = LocalDateTime.parse(textDrawingDate);
        return drawingDate;
    }
}
