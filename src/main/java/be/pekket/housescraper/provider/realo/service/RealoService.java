package be.pekket.housescraper.provider.realo.service;

import be.pekket.housescraper.model.House;
import be.pekket.housescraper.provider.realo.connector.RealoConnector;
import be.pekket.housescraper.provider.realo.mapper.RealoMapper;
import be.pekket.housescraper.provider.realo.model.RealoHouse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RealoService {

    private final RealoConnector realoConnector;
    private final RealoMapper realoMapper;

    public RealoService( RealoConnector realoConnector, RealoMapper realoMapper ) {
        this.realoConnector = realoConnector;
        this.realoMapper = realoMapper;
    }

    public List<House> search() {
        List<House> houses = null;
        List<RealoHouse> elements = realoConnector.getHouses();
        if ( elements != null )
            houses = realoMapper.map(elements);
        return houses;
    }
}
