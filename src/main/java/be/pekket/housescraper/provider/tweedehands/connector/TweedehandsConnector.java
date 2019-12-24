package be.pekket.housescraper.provider.tweedehands.connector;

import be.pekket.housescraper.provider.tweedehands.model.TweedehandsHouse;
import be.pekket.housescraper.provider.tweedehands.model.TweedehandsModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@Service
public class TweedehandsConnector {

    private static final String TWEEDEHANDS_SEARCH_URL = "https://www.2dehands.be/lrp/api/search?asSavedSearch=true&attributesByKey%5B%5D=offeredSince%3A1575990000000&distanceMeters=3000&l1CategoryId=1032&l2CategoryId=2142&limit=30&offset=0&postcode=3600&showSimilarItems=false";
    private RestTemplate restTemplate;

    public TweedehandsConnector( RestTemplate restTemplate ) {
        this.restTemplate = restTemplate;
    }

    public List<TweedehandsHouse> getHouses()  {
        List<TweedehandsHouse> houses = new LinkedList<>();
        ResponseEntity<TweedehandsModel> foundHouses = restTemplate.getForEntity(TWEEDEHANDS_SEARCH_URL, TweedehandsModel.class);

        if ( foundHouses.hasBody() ) {
            TweedehandsModel response = foundHouses.getBody();
            if ( response != null )
                houses = response.getHouses();
        }
        return houses;
    }
}
