package be.pekket.housescraper.provider.immoweb.service;

import be.pekket.housescraper.model.House;
import be.pekket.housescraper.provider.immoweb.connector.ImmoWebConnector;
import be.pekket.housescraper.provider.immoweb.mapper.ImmoWebMapper;
import be.pekket.housescraper.provider.immoweb.model.ImmoWebHouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImmoWebService {
    private static final Logger LOG = LoggerFactory.getLogger(ImmoWebService.class);

    private final ImmoWebConnector immoWebConnector;
    private final ImmoWebMapper immoWebMapper;

    public ImmoWebService( ImmoWebConnector immoWebConnector, ImmoWebMapper immoWebMapper ) {
        this.immoWebConnector = immoWebConnector;
        this.immoWebMapper = immoWebMapper;
    }

    public List<House> search() {
        List<House> houses = null;
        List<ImmoWebHouse> elements = immoWebConnector.getHouses();
        if ( elements != null ){
            houses = immoWebMapper.map(elements);
            LOG.info("immoweb houses {}", houses.size());
        }
        return houses;
    }
}
