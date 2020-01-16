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

    private static final String IMMOWEB_SEARCH_URL = "https://beta.immoweb.be/nl/search-results/huis-en-appartement/te-koop/genk/3600?countries=BE&page=0&orderBy=newest";
    private RestTemplate restTemplate;

    public ImmoWebConnector( RestTemplate restTemplate ) {
        this.restTemplate = restTemplate;
    }

    public List<ImmoWebHouse> getHouses() {
        List<ImmoWebHouse> houses = new LinkedList<>();

        try {
            ResponseEntity<ImmoWebResponse> foundHouses = restTemplate.getForEntity(IMMOWEB_SEARCH_URL, ImmoWebResponse.class);

            if ( foundHouses.hasBody() ) {
                ImmoWebResponse response = foundHouses.getBody();
                if ( response != null )
                    houses = response.getHouses();
            }
        } catch ( Exception e ) {
            System.out.println("Error while getting houses from immoweb " + e.getMessage());
        }

        return houses;
    }
}
