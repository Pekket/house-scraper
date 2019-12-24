package be.pekket.housescraper.provider.immoweb.connector;

import be.pekket.housescraper.provider.immoweb.model.ImmoWebHouse;
import be.pekket.housescraper.provider.immoweb.model.ImmoWebResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@Service
public class ImmoWebConnector {

    private static final String IMMOWEB_SEARCH_URL = "https://beta.immoweb.be/nl/search/get-results?countries=BE&postalCodes=BE-3600__Genk+%283600%29&propertyTypes=HOUSE%2CAPARTMENT&transactionTypes=FOR_SALE&isSoldOrRented=false&page=1&orderBy=newest";
    private RestTemplate restTemplate;

    public ImmoWebConnector( RestTemplate restTemplate ) {
        this.restTemplate = restTemplate;
    }

    public List<ImmoWebHouse> getHouses() {
        List<ImmoWebHouse> houses = new LinkedList<>();
        ResponseEntity<ImmoWebResponse> foundHouses = restTemplate.getForEntity(IMMOWEB_SEARCH_URL, ImmoWebResponse.class);

        if ( foundHouses.hasBody() ) {
            ImmoWebResponse response = foundHouses.getBody();
            if ( response != null )
                houses = response.getHouses();
        }

        return houses;
    }
}
