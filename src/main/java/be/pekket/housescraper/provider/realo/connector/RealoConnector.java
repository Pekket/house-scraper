package be.pekket.housescraper.provider.realo.connector;

import be.pekket.housescraper.provider.realo.model.RealoHouse;
import be.pekket.housescraper.provider.realo.model.RealoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@Service
public class RealoConnector {

    private static final String REALO_SEARCH_URL = "https://www.realo.be/en/search/estates.json?boundaryAddressIds=5387959&ways[]=SALE&types[]=HOUSE&types[]=APARTMENT&types[]=ROOM&types[]=OFFICE&types[]=INDUSTRIAL&types[]=BUSINESS&types[]=LAND&types[]=PARKING&types[]=INVESTMENT_PROPERTY&types[]=NEWBUILD_PROJECT&page=0&mobile=true&isMapSearch=false&isListSearch=true&isLocationChange=false";
    private RestTemplate restTemplate;

    public RealoConnector( RestTemplate restTemplate ) {
        this.restTemplate = restTemplate;
    }

    public List<RealoHouse> getHouses() {
        List<RealoHouse> houses = new LinkedList<>();

        try {
            ResponseEntity<RealoResponse> foundHouses = restTemplate.getForEntity(REALO_SEARCH_URL, RealoResponse.class);

            if ( foundHouses.hasBody() ) {
                RealoResponse response = foundHouses.getBody();
                if ( response != null )
                    // nice :)
                    houses = response.getData().getList().getAssigns().getRealoListGrid().getData().getItems();
            }
        } catch ( Exception e ) {
            System.out.println("Error while getting houses from realo " + e.getMessage());
        }

        return houses;
    }
}
