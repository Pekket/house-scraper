package be.pekket.housescraper.immovlan.mapper;

import be.pekket.housescraper.immovlan.model.ImmoVlanHouse;
import be.pekket.housescraper.model.House;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ImmoVlanMapper {
    private static final String IMMOVLAN_BASE_URL = "https://immo.vlan.be/";

    public List<House> map( List<ImmoVlanHouse> elements ) {
        List<House> houses = new LinkedList<>();

        for(ImmoVlanHouse ivHouse : elements) {
            House house = House.builder()
                    .title(ivHouse.getTitle())
                    .url(IMMOVLAN_BASE_URL + ivHouse.getUrl())
                    .address(ivHouse.getAddress().toString())
                    .price(ivHouse.getFinancial().getPrice())
                    .agency(ivHouse.getOwner().getAgency())
                    .build();

            if(house != null){
                houses.add(house);
            }
        }
        return houses;
    }
}
