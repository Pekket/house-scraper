package be.pekket.housescraper.service;

import be.pekket.housescraper.exception.ScraperException;
import be.pekket.housescraper.model.House;
import be.pekket.housescraper.provider.Provider;
import be.pekket.housescraper.provider.immoscoop.service.ImmoscoopService;
import be.pekket.housescraper.provider.immovlan.service.ImmoVlanService;
import be.pekket.housescraper.provider.immoweb.service.ImmoWebService;
import be.pekket.housescraper.provider.tweedehands.service.TweedehandsService;
import be.pekket.housescraper.provider.zimmo.service.ZimmoService;
import be.pekket.housescraper.repository.HouseRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseService {

    private HouseRepository houseRepository;
    private ZimmoService zimmoService;
    private ImmoVlanService immoVlanService;
    private ImmoWebService immoWebService;
    private ImmoscoopService immoscoopService;
    private TweedehandsService tweedehandsService;
    private WebhookService webhookService;

    public HouseService( HouseRepository houseRepository, ZimmoService zimmoService, ImmoVlanService immoVlanService,
                         ImmoWebService immoWebService, ImmoscoopService immoscoopService, TweedehandsService tweedehandsService, WebhookService webhookService ) {
        this.houseRepository = houseRepository;
        this.zimmoService = zimmoService;
        this.immoVlanService = immoVlanService;
        this.immoWebService = immoWebService;
        this.immoscoopService = immoscoopService;
        this.tweedehandsService = tweedehandsService;
        this.webhookService = webhookService;
    }

    @Scheduled(cron = "0 0/15 * * * ?")
    public void processHouses() {
        List<House> newHouses = new LinkedList<>();
        try {
            List<House> foundHouses = immoscoopService.search();
            foundHouses.addAll(immoVlanService.search());
            foundHouses.addAll(immoWebService.search());
            foundHouses.addAll(zimmoService.search());
            foundHouses.addAll(tweedehandsService.search());

            for ( House house : foundHouses ) {
                if ( Provider.TWEEDEHANDS.equals(house.getProvider()) || Provider.IMMOSCOOP.equals(house.getProvider()) ) {
                    if ( !houseRepository.existsHouseByUrl(house.getUrl()) )
                        newHouses.add(houseRepository.save(house));
                } else if ( !houseRepository.existsHouseByAddress(house.getAddress()) )
                    newHouses.add(houseRepository.save(house));
            }

            if ( !newHouses.isEmpty() ) {
              //  webhookService.send(newHouses.size());
            }
        } catch ( ScraperException e ) {
            System.out.println("Oopsie error " + e.getMessage());
        }
    }

    public List<House> getLastHouses(String addressQuery, String providers) {
        List<Provider> providersList = Arrays.stream(providers.split(","))
                .map(Provider::byValue)
                .collect(Collectors.toList());

        return houseRepository.findTop30ByAddressContainingIgnoreCaseAndProviderInOrderByTimestampDesc(addressQuery, providersList);
    }
}
