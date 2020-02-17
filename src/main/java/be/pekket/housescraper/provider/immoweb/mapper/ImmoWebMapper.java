package be.pekket.housescraper.provider.immoweb.mapper;

import be.pekket.housescraper.provider.ProviderType;
import be.pekket.housescraper.provider.immoweb.model.ImmoWebHouse;
import be.pekket.housescraper.model.House;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ImmoWebMapper {

    private static final String BASE_URL = "https://immoweb.be/nl/bericht/appartement/te-koop/";

    public List<House> map( List<ImmoWebHouse> elements ) {
        List<House> houses = new LinkedList<>();

        for(ImmoWebHouse immoHouse : elements) {
            House house = House.builder()
                    .timestamp(System.currentTimeMillis())
                    .provider(ProviderType.IMMOWEB)
                    .title(immoHouse.getProperty().getTitle())
                    .url(BASE_URL + immoHouse.getId())
                    .address(immoHouse.getProperty().getAddress().toString())
                    .price(immoHouse.getPrice().getAmount())
                    .agency(immoHouse.getAgency())
                    .imgUrl(immoHouse.getFirstImage())
                    .build();

            if(house != null){
                houses.add(house);
            }
        }
        return houses;
    }

}
