package be.pekket.housescraper.provider.tweedehands.mapper;

import be.pekket.housescraper.model.House;
import be.pekket.housescraper.provider.Provider;
import be.pekket.housescraper.provider.tweedehands.model.TweedehandsHouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class TweedehandsMapper {
    private static final Logger LOG = LoggerFactory.getLogger(TweedehandsMapper.class);

    private static final String BASE_URL = "https://www.2dehands.be";

    public List<House> map( List<TweedehandsHouse> tweedehandsHouses ) {
        List<House> houses = new LinkedList<>();

        for( TweedehandsHouse tweedehandsHouse : tweedehandsHouses) {
            House house = House.builder()
                    .timestamp(System.currentTimeMillis())
                    .provider(Provider.TWEEDEHANDS)
                    .title(tweedehandsHouse.getTitle())
                    .url(BASE_URL + tweedehandsHouse.getUrl())
                    .address(null)
                    .price(tweedehandsHouse.getPriceInfo().getPrice())
                    .agency(tweedehandsHouse.getSellerInformation().getName())
                    .imgUrl(getImageUrl(tweedehandsHouse.getImgUrls()))
                    .build();

            if(house != null){
                LOG.info("Found tweedehands house {} {}", house.getTitle(), house.getImgUrl());
                houses.add(house);
            }
        }
        return houses;
    }

    private String getImageUrl(String[] imgUrls) {
        String result = null;
        if(imgUrls != null && imgUrls.length > 0) {
            result =  "https:" + imgUrls[0];
        }
        return result;
    }
}
