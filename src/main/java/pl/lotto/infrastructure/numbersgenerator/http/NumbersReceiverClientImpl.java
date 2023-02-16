package pl.lotto.infrastructure.numbersgenerator.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NumbersReceiverClientImpl implements NumbersReceiverClient {

    private final RestTemplate restTemplate;

    @Value("${receiverFacadeFacade.url:http://localhost}")
    private String receiverFacadeFacadeUrl;

    @Value("${receiverFacadeFacade.port:8088}")
    private String receiverFacadeFacadePort;

    public NumbersReceiverClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public DrawDateDto specifyDrawDate() {
        String url = receiverFacadeFacadeUrl + ":" + receiverFacadeFacadePort + "/drawDate";
        ResponseEntity<DrawDateDto> response = restTemplate.getForEntity(url, DrawDateDto.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new IllegalStateException("Unexpected status code: " + response.getStatusCodeValue());
        }
    }
}
