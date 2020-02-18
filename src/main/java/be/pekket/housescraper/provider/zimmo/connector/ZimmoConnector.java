package be.pekket.housescraper.provider.zimmo.connector;


import be.pekket.housescraper.exception.ScraperException;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ZimmoConnector {
    private static final Logger LOG = LoggerFactory.getLogger(ZimmoConnector.class);

    private static final String[] STORE_COOKIES = {"__uzma", "__uzmb", "__uzmc", "__uzmd", "ci_session"};
    private static final String ZIMMO_SEARCH_URL = "https://www.zimmo.be/nl/panden/?status=1&hash=1f5327243931e61e1b49752c28727194&priceIncludeUnknown=1&priceChangedOnly=0&bedroomsIncludeUnknown=1&bathroomsIncludeUnknown=1&constructionIncludeUnknown=1&livingAreaIncludeUnknown=1&landAreaIncludeUnknown=1&commercialAreaIncludeUnknown=1&yearOfConstructionIncludeUnknown=1&epcIncludeUnknown=1&queryCondition=and&includeNoPhotos=1&includeNoAddress=1&onlyRecent=1&onlyRecentlyUpdated=0&isPlus=0&region=list&district=MzAY0sAQAA%253D%253D&sort=recent&sort_order=desc#gallery";
    private static final String DOMAIN = "www.zimmo.be";
    private Map<String, String> cookies = new HashMap<>();

    public List<DomNode> getHouseDomElements() throws ScraperException {
        List<DomNode> results = null;
        try ( final WebClient webClient = new WebClient() ) {
            webClient.getOptions().setThrowExceptionOnScriptError(false);

            CookieManager cookieManager = webClient.getCookieManager();
            cookies.keySet().stream()
                    .map(c -> new Cookie(DOMAIN, c, cookies.get(c)))
                    .forEach(cookieManager::addCookie);

            final HtmlPage page = webClient.getPage(ZIMMO_SEARCH_URL);
            addCookies(cookieManager);
            results = page.getByXPath("//div[@class='property-results_container']/div[contains(@class,'property-item')]");
        } catch ( IOException e ) {
            throw new ScraperException("Error connecting to Zimmo " + e.getMessage());
        }
        return results;
    }


    private void addCookies( CookieManager cookieManager) {
        Arrays.stream(STORE_COOKIES)
                .filter(s -> cookieManager.getCookie(s) != null)
                .map(cookieManager::getCookie)
                .forEach(cookie -> cookies.put(cookie.getName(), cookie.getValue()));

        LOG.info("Stored new session cookie");
    }
}
