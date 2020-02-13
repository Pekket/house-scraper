package be.pekket.housescraper.provider.zimmo.service;

import be.pekket.housescraper.exception.ScraperException;
import be.pekket.housescraper.model.House;
import be.pekket.housescraper.provider.immovlan.service.ImmoVlanService;
import be.pekket.housescraper.provider.zimmo.connector.ZimmoConnector;
import be.pekket.housescraper.provider.zimmo.mapper.ZimmoMapper;
import com.gargoylesoftware.htmlunit.html.DomNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZimmoService {
    private static final Logger LOG = LoggerFactory.getLogger(ImmoVlanService.class);

    private final ZimmoConnector zimmoConnector;
    private final ZimmoMapper zimmoMapper;

    public ZimmoService( ZimmoConnector zimmoConnector, ZimmoMapper zimmoMapper ) {
        this.zimmoConnector = zimmoConnector;
        this.zimmoMapper = zimmoMapper;
    }

    public List<House> search() throws ScraperException {
        List<House> houses = null;
        List<DomNode> elements = zimmoConnector.getHouseDomElements();
        if(elements != null ){
            houses = zimmoMapper.map(elements);
            LOG.info("zimmo houses {}", houses.size());
        }
        return houses;
    }
}
