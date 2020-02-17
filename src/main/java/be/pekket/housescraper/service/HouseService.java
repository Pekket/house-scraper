package be.pekket.housescraper.service;

import be.pekket.housescraper.exception.ScraperException;
import be.pekket.housescraper.model.House;
import be.pekket.housescraper.provider.ProviderType;
import be.pekket.housescraper.provider.immoscoop.service.ImmoscoopService;
import be.pekket.housescraper.provider.immovlan.service.ImmoVlanService;
import be.pekket.housescraper.provider.immoweb.service.ImmoWebService;
import be.pekket.housescraper.provider.realo.service.RealoService;
import be.pekket.housescraper.provider.tweedehands.service.TweedehandsService;
import be.pekket.housescraper.provider.zimmo.service.ZimmoService;
import be.pekket.housescraper.repository.HouseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseService {
    private static final Logger LOG = LoggerFactory.getLogger(HouseService.class);

    private HouseRepository houseRepository;
    private ZimmoService zimmoService;
    private ImmoVlanService immoVlanService;
    private ImmoWebService immoWebService;
    private ImmoscoopService immoscoopService;
    private TweedehandsService tweedehandsService;
    private RealoService realoService;
    private WebhookService webhookService;

    public HouseService( HouseRepository houseRepository, ZimmoService zimmoService, ImmoVlanService immoVlanService,
                         ImmoWebService immoWebService, ImmoscoopService immoscoopService, TweedehandsService tweedehandsService, RealoService realoService, WebhookService webhookService ) {
        this.houseRepository = houseRepository;
        this.zimmoService = zimmoService;
        this.immoVlanService = immoVlanService;
        this.immoWebService = immoWebService;
        this.immoscoopService = immoscoopService;
        this.tweedehandsService = tweedehandsService;
        this.realoService = realoService;
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
            foundHouses.addAll(realoService.search());

            LOG.info("total houses found {}", foundHouses.size());
            for ( House house : foundHouses ) {
                if ( house.getAddress() == null || ProviderType.TWEEDEHANDS.equals(house.getProvider()) || ProviderType.IMMOSCOOP.equals(house.getProvider()) ) {
                    if ( !houseRepository.existsHouseByUrl(house.getUrl()) )
                        newHouses.add(houseRepository.save(house));
                } else if ( !houseRepository.existsHouseByAddress(house.getAddress()) )
                    newHouses.add(houseRepository.save(house));
            }

            LOG.info("new houses found {}", newHouses.size());
            if ( !newHouses.isEmpty() ) {
                webhookService.send(newHouses.size());
            }
        } catch ( ScraperException e ) {
            LOG.error("Oopsie error {}", e.getMessage());
        }
    }

    public List<House> getLastHouses( String addressQuery, String providers ) {
        List<ProviderType> providersList = Arrays.stream(providers.split(","))
                .map(ProviderType::byValue)
                .collect(Collectors.toList());

        List<House> houses;
        if ( StringUtils.isEmpty(addressQuery) )
            houses = houseRepository.findTop30ByProviderInOrderByTimestampDesc(providersList);
        else
            houses = houseRepository.findTop30ByAddressContainingIgnoreCaseAndProviderInOrderByTimestampDesc(addressQuery, providersList);

        return houses;
    }
}
