package pl.lotto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NumberGeneratorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NumberGeneratorServiceApplication.class, args);
    }

}
