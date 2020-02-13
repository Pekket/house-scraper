package be.pekket.housescraper.provider.tweedehands.service;

import be.pekket.housescraper.model.House;
import be.pekket.housescraper.provider.tweedehands.connector.TweedehandsConnector;
import be.pekket.housescraper.provider.tweedehands.mapper.TweedehandsMapper;
import be.pekket.housescraper.provider.tweedehands.model.TweedehandsHouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweedehandsService {
    private static final Logger LOG = LoggerFactory.getLogger(TweedehandsService.class);

    private TweedehandsConnector tweedehandsConnector;
    private TweedehandsMapper tweedehandsMapper;

    public TweedehandsService( TweedehandsConnector tweedehandsConnector, TweedehandsMapper tweedehandsMapper ) {
        this.tweedehandsConnector = tweedehandsConnector;
        this.tweedehandsMapper = tweedehandsMapper;
    }

    public List<House> search() {
        List<House> houses = null;
        List<TweedehandsHouse> elements = tweedehandsConnector.getHouses();
        if ( elements != null ){
            houses = tweedehandsMapper.map(elements);
            LOG.info("tweedehands houses {}", houses.size());
        }
        return houses;
    }
}
