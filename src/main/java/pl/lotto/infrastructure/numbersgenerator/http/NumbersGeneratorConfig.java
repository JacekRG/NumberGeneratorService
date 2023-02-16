package pl.lotto.infrastructure.numbersgenerator.http;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class NumbersGeneratorConfig {

    @Bean
   public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
