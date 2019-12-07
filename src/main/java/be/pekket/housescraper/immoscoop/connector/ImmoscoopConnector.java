package be.pekket.housescraper.immoscoop.connector;

import be.pekket.housescraper.exception.ScraperException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ImmoscoopConnector {

    public static final String IMMOSCOOP_SEARCH_URL = "https://www.immoscoop.be/immo/genk/koop/sorteerop-date/";


    public List<DomNode> getHouseDomElements() throws ScraperException {
        List<DomNode> results = null;
        try ( final WebClient webClient = new WebClient() ) {
            webClient.getOptions().setThrowExceptionOnScriptError(false);

            final HtmlPage page = webClient.getPage(IMMOSCOOP_SEARCH_URL);
            results = page.getByXPath("//article[@class='search-result-position']");
        } catch ( IOException e ) {
            throw new ScraperException("Error connecting to Immoscoop " + e.getMessage());
        }
        return results;
    }
}
