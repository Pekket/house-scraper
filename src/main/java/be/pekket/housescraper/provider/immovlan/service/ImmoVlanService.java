package be.pekket.housescraper.provider.immovlan.service;


import be.pekket.housescraper.model.House;
import be.pekket.housescraper.provider.immovlan.connector.ImmoVlanConnector;
import be.pekket.housescraper.provider.immovlan.mapper.ImmoVlanMapper;
import be.pekket.housescraper.provider.immovlan.model.ImmoVlanHouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImmoVlanService {
    private static final Logger LOG = LoggerFactory.getLogger(ImmoVlanService.class);

    private final ImmoVlanConnector immoVlanConnector;
    private final ImmoVlanMapper immoVlanMapper;

    public ImmoVlanService( ImmoVlanConnector immoVlanConnector, ImmoVlanMapper immoVlanMapper ) {
        this.immoVlanConnector = immoVlanConnector;
        this.immoVlanMapper = immoVlanMapper;
    }

    public List<House> search()  {
        List<House> houses = null;
        List<ImmoVlanHouse> elements = immoVlanConnector.getHouses();
        if(elements != null ) {
            houses = immoVlanMapper.map(elements);
            LOG.info("immovlan houses {}", houses.size());
        }
        return houses;
    }
}
