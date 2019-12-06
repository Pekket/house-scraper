package be.pekket.houseScraper.zimmo.service;

import be.pekket.houseScraper.exception.ScraperException;
import be.pekket.houseScraper.model.House;
import be.pekket.houseScraper.zimmo.connector.ZimmoConnector;
import be.pekket.houseScraper.zimmo.mapper.ZimmoMapper;
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
