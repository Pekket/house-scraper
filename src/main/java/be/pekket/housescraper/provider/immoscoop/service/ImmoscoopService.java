package be.pekket.housescraper.provider.immoscoop.service;

import be.pekket.housescraper.exception.ScraperException;
import be.pekket.housescraper.model.House;
import be.pekket.housescraper.provider.immoscoop.connector.ImmoscoopConnector;
import be.pekket.housescraper.provider.immoscoop.mapper.ImmoscoopMapper;
import com.gargoylesoftware.htmlunit.html.DomNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImmoscoopService {
    private static final Logger LOG = LoggerFactory.getLogger(ImmoscoopService.class);

    private ImmoscoopConnector immoscoopConnector;
    private ImmoscoopMapper immoscoopMapper;

    public ImmoscoopService( ImmoscoopConnector immoscoopConnector, ImmoscoopMapper immoscoopMapper ) {
        this.immoscoopConnector = immoscoopConnector;
        this.immoscoopMapper = immoscoopMapper;
    }

    public List<House> search() throws ScraperException {
        List<House> houses = null;
        List<DomNode> elements = immoscoopConnector.getHouseDomElements();
        if ( elements != null ) {
            houses = immoscoopMapper.map(elements);
            LOG.info("immoscoop houses {}", houses.size());
        }

        return houses;
    }
}
