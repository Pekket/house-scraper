package be.pekket.housescraper.provider.immoscoop.service;

import be.pekket.housescraper.exception.ScraperException;
import be.pekket.housescraper.provider.immoscoop.connector.ImmoscoopConnector;
import be.pekket.housescraper.provider.immoscoop.mapper.ImmoscoopMapper;
import be.pekket.housescraper.model.House;
import com.gargoylesoftware.htmlunit.html.DomNode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImmoscoopService {

    private ImmoscoopConnector immoscoopConnector;
    private ImmoscoopMapper immoscoopMapper;

    public ImmoscoopService( ImmoscoopConnector immoscoopConnector, ImmoscoopMapper immoscoopMapper ) {
        this.immoscoopConnector = immoscoopConnector;
        this.immoscoopMapper = immoscoopMapper;
    }

    public List<House> search() throws ScraperException {
        List<House> houses = null;
        List<DomNode> elements = immoscoopConnector.getHouseDomElements();
        if(elements != null )
            houses = immoscoopMapper.map(elements);
        return houses;
    }
}
