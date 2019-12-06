package be.pekket.housescraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HouseScraperApplication {

    public static void main( String[] args ) { SpringApplication.run(HouseScraperApplication.class, args); }
}
