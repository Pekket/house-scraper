package be.pekket.housescraper.service;

import be.pekket.housescraper.exception.ScraperException;
import be.pekket.housescraper.immoscoop.service.ImmoscoopService;
import be.pekket.housescraper.immovlan.service.ImmoVlanService;
import be.pekket.housescraper.immoweb.service.ImmoWebService;
import be.pekket.housescraper.model.House;
import be.pekket.housescraper.repository.HouseRepository;
import be.pekket.housescraper.zimmo.service.ZimmoService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class HouseService {

    private HouseRepository houseRepository;
    private ZimmoService zimmoService;
    private ImmoVlanService immoVlanService;
    private ImmoWebService immoWebService;
    private ImmoscoopService immoscoopService;
    private WebhookService webhookService;

    public HouseService( HouseRepository houseRepository, ZimmoService zimmoService, ImmoVlanService immoVlanService,
                         ImmoWebService immoWebService, ImmoscoopService immoscoopService, WebhookService webhookService ) {
        this.houseRepository = houseRepository;
        this.zimmoService = zimmoService;
        this.immoVlanService = immoVlanService;
        this.immoWebService = immoWebService;
        this.immoscoopService = immoscoopService;
        this.webhookService = webhookService;
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void processHouses() {
        List<House> newHouses = new LinkedList<>();
        try {
            List<House> foundHouses =  zimmoService.search();
            foundHouses.addAll(immoVlanService.search());
            foundHouses.addAll(immoWebService.search());
            foundHouses.addAll(immoscoopService.search());

            for ( House house : foundHouses ) {
                if(!houseRepository.existsHouseByAddress(house.getAddress())) {
                    newHouses.add(houseRepository.save(house));
                }
            }

            if(!newHouses.isEmpty()) {
                Collections.shuffle(newHouses);
                webhookService.send(newHouses.size());
            }
        } catch ( ScraperException e ) {
            System.out.println("Oopsie error " + e.getMessage());
        }
    }




    public List<House> getLastHouses() {
        return houseRepository.findTop20ByOrderByTimestampDesc();
    }
}
