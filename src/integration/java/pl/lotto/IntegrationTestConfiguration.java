package pl.lotto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;


@Configuration
@Profile("integration")
public class IntegrationTestConfiguration {

    @Bean
    @Primary
    AdjustableClock adjustableClock() {
        LocalDateTime friday = LocalDateTime.of(2022, 12, 22, 11, 0, 0);
        return new AdjustableClock(friday.toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
    }
    @Bean
    @Primary
    AdjustableNumberGenerator  adjustableNumberGenerator() {
        return new AdjustableNumberGenerator();
    }

}
