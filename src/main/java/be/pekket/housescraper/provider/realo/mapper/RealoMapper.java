package be.pekket.housescraper.provider.realo.mapper;

import be.pekket.housescraper.model.House;
import be.pekket.housescraper.provider.Provider;
import be.pekket.housescraper.provider.realo.model.RealoHouse;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class RealoMapper {

    public List<House> map( List<RealoHouse> elements ) {
        List<House> houses = new LinkedList<>();

        for ( RealoHouse realoHouse : elements ) {
            House house = House.builder()
                    .timestamp(System.currentTimeMillis())
                    .provider(Provider.REALO)
                    .title(null)
                    .url(realoHouse.getUrl())
                    .address(realoHouse.getAddress() != null ? realoHouse.getAddress().toString() : null)
                    .price(realoHouse.getPrice())
                    .agency(null)
                    .imgUrl(realoHouse.getImage() != null ? realoHouse.getImage().getUrl() : null)
                    .build();

            if ( house != null ) {
                houses.add(house);
            }
        }
        return houses;
    }

}
