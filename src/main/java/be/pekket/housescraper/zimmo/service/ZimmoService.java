package be.pekket.housescraper.zimmo.service;

import be.pekket.housescraper.exception.ScraperException;
import be.pekket.housescraper.model.House;
import be.pekket.housescraper.zimmo.connector.ZimmoConnector;
import be.pekket.housescraper.zimmo.mapper.ZimmoMapper;
import com.gargoylesoftware.htmlunit.html.DomNode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZimmoService {

    private final ZimmoConnector zimmoConnector;
    private final ZimmoMapper zimmoMapper;

    public ZimmoService( ZimmoConnector zimmoConnector, ZimmoMapper zimmoMapper ) {
        this.zimmoConnector = zimmoConnector;
        this.zimmoMapper = zimmoMapper;
    }

    public List<House> search() throws ScraperException {
        List<House> houses = null;
        List<DomNode> elements = zimmoConnector.getHouseDomElements();
        if(elements != null )
            houses = zimmoMapper.map(elements);
        return houses;
    }
}
