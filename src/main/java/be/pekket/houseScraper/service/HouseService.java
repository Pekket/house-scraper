package be.pekket.houseScraper.service;

import be.pekket.houseScraper.exception.ScraperException;
import be.pekket.houseScraper.model.House;
import be.pekket.houseScraper.repository.HouseRepository;
import be.pekket.houseScraper.zimmo.service.ZimmoService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class HouseService {

    private HouseRepository houseRepository;
    private ZimmoService zimmoService;
    private WebhookService webhookService;

    public HouseService( HouseRepository houseRepository, ZimmoService zimmoService, WebhookService webhookService ) {
        this.houseRepository = houseRepository;
        this.zimmoService = zimmoService;
        this.webhookService = webhookService;
    }


    @Scheduled(cron = "0 0/10 * * * ?")
    public void processHouses() {
        List<House> newHouses = new LinkedList<>();
        try {
            List<House> zimmoHouses = zimmoService.search();

            for ( House house : zimmoHouses ) {
                if(!houseRepository.existsHouseByAddress(house.getAddress())) {
                    newHouses.add(houseRepository.save(house));
                }
            }

            if(!newHouses.isEmpty()) {
                webhookService.send(newHouses.size());
            }
        } catch ( ScraperException e ) {
            System.out.println("Oopsie error " + e.getMessage());
        }
    }
}
