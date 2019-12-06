package be.pekket.housescraper.immovlan.connector;


import be.pekket.housescraper.immovlan.model.ImmoVlanHouse;
import be.pekket.housescraper.immovlan.model.ImmoVlanResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@Service
public class ImmoVlanConnector {

    private static final String IMMOVLAN_SEARCH_URL = "https://immo.vlan.be/nl/phoenix/search";
    private static final String IMMOVLAN_SEARCH_BODY = "{\"showBreadcrumbs\":false,\"showInlinks\":false,\"pageOffset\":1,\"pageSize\":30,\"cityTermsAggregationFilter\":[{\"hasSuburbs\":false,\"hasParent\":false,\"id\":9121,\"label\":\"Genk (3600)\",\"slug\":\"3600-genk\"}],\"cityGroupTermsAggregationFilter\":[],\"provinceTermsAggregationFilter\":[],\"regionTermsAggregationFilter\":[],\"countryTermsAggregationFilter\":[{\"id\":318,\"label\":\"België\",\"slug\":\"belgie\"}],\"propertyTypeTermsAggregationFilter\":[{\"id\":\"house\",\"label\":\"Huis\",\"slug\":\"huis\"}],\"propertySubTypeTermsAggregationFilter\":[{\"id\":\"House\",\"label\":\"Huis\",\"slug\":\"huis\"},{\"id\":\"Villa\",\"label\":\"Villa\",\"slug\":\"villa\"},{\"id\":\"Bungalow\",\"label\":\"Bungalow\",\"slug\":\"bungalow\"},{\"id\":\"Chalet\",\"label\":\"Chalet\",\"slug\":\"chalet\"},{\"id\":\"Cottage\",\"label\":\"Fermette\",\"slug\":\"fermette\"},{\"id\":\"MasterHouse\",\"label\":\"Herenhuis\",\"slug\":\"herenhuis\"},{\"id\":\"Mansion\",\"label\":\"Kasteel\",\"slug\":\"kasteel\"},{\"id\":\"MixedBuilding\",\"label\":\"Huis gemengd gebruik\",\"slug\":\"huis-gemengd-gebruik\"}],\"transactionTypeTermsAggregationFilter\":[{\"id\":\"sale\",\"label\":\"te koop\",\"slug\":\"te-koop\"},{\"id\":\"publicSale\",\"label\":\"in openbare verkoop\",\"slug\":\"in-openbare-verkoop\"}],\"tagTermsAggregationFilter\":[],\"stateTermsAggregationFilter\":[],\"roomTermsAggregationFilter\":[],\"bedroomTermsAggregationFilter\":[],\"bathroomTermsAggregationFilter\":[],\"facadeTermsAggregationFilter\":[],\"userTypeTermsAggregationFilter\":[],\"userIdTermsAggregationFilter\":[],\"priceRangeAggregationFilter\":{},\"totalSurfaceRangeAggregationFilter\":{},\"livableSurfaceRangeAggregationFilter\":{},\"radiusFilter\":{},\"mapRectangleFilter\":{},\"target\":\"property\",\"sortOrderDirection\":\"ascending\",\"sortOrderField\":\"\",\"trackingGuid\":\"H03ktFLHpRdGtbJEWA7uIAqW2RKonYf2\"}";
    private final RestTemplate restTemplate;

    public ImmoVlanConnector( RestTemplate restTemplate ) {
        this.restTemplate = restTemplate;
    }

    public List<ImmoVlanHouse> getHouses() {
        List<ImmoVlanHouse> houses = new LinkedList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request =
                new HttpEntity<String>(IMMOVLAN_SEARCH_BODY, headers);

        ResponseEntity<ImmoVlanResponse> foundHouses = restTemplate.postForEntity(IMMOVLAN_SEARCH_URL, request, ImmoVlanResponse.class);

        if ( foundHouses.hasBody() ) {
            ImmoVlanResponse response = foundHouses.getBody();
            if ( response != null )
                houses = response.getHouses();
        }

        return houses;
    }
}
