package be.pekket.housescraper.provider.immovlan.service;


import be.pekket.housescraper.provider.immovlan.connector.ImmoVlanConnector;
import be.pekket.housescraper.provider.immovlan.mapper.ImmoVlanMapper;
import be.pekket.housescraper.provider.immovlan.model.ImmoVlanHouse;
import be.pekket.housescraper.model.House;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImmoVlanService {

    private final ImmoVlanConnector immoVlanConnector;
    private final ImmoVlanMapper immoVlanMapper;

    public ImmoVlanService( ImmoVlanConnector immoVlanConnector, ImmoVlanMapper immoVlanMapper ) {
        this.immoVlanConnector = immoVlanConnector;
        this.immoVlanMapper = immoVlanMapper;
    }

    public List<House> search()  {
        List<House> houses = null;
        List<ImmoVlanHouse> elements = immoVlanConnector.getHouses();
        if(elements != null )
            houses = immoVlanMapper.map(elements);
        return houses;
    }
}
