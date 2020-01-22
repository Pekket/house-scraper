package be.pekket.housescraper.provider.immovlan.mapper;

import be.pekket.housescraper.provider.Provider;
import be.pekket.housescraper.provider.immovlan.model.ImmoVlanHouse;
import be.pekket.housescraper.model.House;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ImmoVlanMapper {
    private static final Logger LOG = LoggerFactory.getLogger(ImmoVlanMapper.class);

    private static final String IMMOVLAN_BASE_URL = "https://immo.vlan.be/";
    private static final String IMMOVLAN_IMG_BASE_URL = "https://file.immo.vlan.be/ImageHandler/PropertySize/";

    public List<House> map( List<ImmoVlanHouse> elements ) {
        List<House> houses = new LinkedList<>();

        for(ImmoVlanHouse ivHouse : elements) {
            House house = House.builder()
                    .timestamp(System.currentTimeMillis())
                    .provider(Provider.IMMOVLAN)
                    .title(ivHouse.getTitle())
                    .url(IMMOVLAN_BASE_URL + ivHouse.getUrl())
                    .address(ivHouse.getAddress().toString())
                    .price(ivHouse.getFinancial().getPrice())
                    .agency(ivHouse.getOwner().getAgency())
                    .imgUrl(IMMOVLAN_IMG_BASE_URL + ivHouse.getImgUrl())
                    .build();

            if(house != null){
                houses.add(house);
            }
        }
        return houses;
    }
}
