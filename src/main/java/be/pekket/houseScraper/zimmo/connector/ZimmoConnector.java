package be.pekket.houseScraper.zimmo.connector;


import be.pekket.houseScraper.exception.ScraperException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ZimmoConnector {
    public static final String ZIMMO_SEARCH_URL = "https://www.zimmo.be/nl/panden/?status=1&hash=1f5327243931e61e1b49752c28727194&priceIncludeUnknown=1&priceChangedOnly=0&bedroomsIncludeUnknown=1&bathroomsIncludeUnknown=1&constructionIncludeUnknown=1&livingAreaIncludeUnknown=1&landAreaIncludeUnknown=1&commercialAreaIncludeUnknown=1&yearOfConstructionIncludeUnknown=1&epcIncludeUnknown=1&queryCondition=and&includeNoPhotos=1&includeNoAddress=1&onlyRecent=1&onlyRecentlyUpdated=0&isPlus=0&region=list&district=MzAY0sAQAA%253D%253D&sort=recent&sort_order=desc#gallery";

    public List<DomNode> getHouseDomElements() throws ScraperException {
        List<DomNode> results = null;
        try ( final WebClient webClient = new WebClient() ) {
            webClient.getOptions().setThrowExceptionOnScriptError(false);

            final HtmlPage page = webClient.getPage(ZIMMO_SEARCH_URL);
            results = page.getByXPath("//div[@class='property-item ']");
        } catch ( IOException e ) {
            throw new ScraperException("Error connecting to Zimmo " + e.getMessage());
        }
        return results;
    }
}
