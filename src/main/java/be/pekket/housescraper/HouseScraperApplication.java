package be.pekket.housescraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class HouseScraperApplication {

    public static void main( String[] args ) {
        ApplicationContext context = SpringApplication.run(HouseScraperApplication.class, args);
//        HouseService service = context.getBean(HouseService.class);
//        service.processHouses();
    }

    @Bean
    public RestTemplate restTemplate( RestTemplateBuilder builder) {
        return builder.build();
    }
}
