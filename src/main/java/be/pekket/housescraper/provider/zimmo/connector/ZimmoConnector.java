package be.pekket.housescraper.provider.zimmo.connector;


import be.pekket.housescraper.exception.ScraperException;
import be.pekket.housescraper.service.ProviderService;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

import static be.pekket.housescraper.provider.ProviderType.ZIMMO;

@Service
public class ZimmoConnector {
    private static final String ZIMMO_SEARCH_URL = "https://www.zimmo.be/nl/panden/?status=1&hash=1f5327243931e61e1b49752c28727194&priceIncludeUnknown=1&priceChangedOnly=0&bedroomsIncludeUnknown=1&bathroomsIncludeUnknown=1&constructionIncludeUnknown=1&livingAreaIncludeUnknown=1&landAreaIncludeUnknown=1&commercialAreaIncludeUnknown=1&yearOfConstructionIncludeUnknown=1&epcIncludeUnknown=1&queryCondition=and&includeNoPhotos=1&includeNoAddress=1&onlyRecent=1&onlyRecentlyUpdated=0&isPlus=0&region=list&district=MzAY0sAQAA%253D%253D&sort=recent&sort_order=desc#gallery";
    private static final String SESSION_COOKIE = "ci_session";

    private final ProviderService providerService;

    public ZimmoConnector( ProviderService providerService ) {
        this.providerService = providerService;
    }

    public List<DomNode> getHouseDomElements() throws ScraperException {
        List<DomNode> results = null;
        try ( final WebClient webClient = new WebClient() ) {
            webClient.getOptions().setThrowExceptionOnScriptError(false);

            String sessionCookie = providerService.getCookie(ZIMMO, SESSION_COOKIE);
            if ( !StringUtils.isEmpty(sessionCookie) ) {
                webClient.getCookieManager().addCookie( new Cookie("www.zimmo.be", SESSION_COOKIE, sessionCookie));
            }

            final HtmlPage page = webClient.getPage(ZIMMO_SEARCH_URL);
            if ( StringUtils.isEmpty(sessionCookie) ) {
                Cookie cookie = webClient.getCookieManager().getCookie(SESSION_COOKIE);
                providerService.storeCookie(ZIMMO, SESSION_COOKIE, cookie.getValue());
            }

            results = page.getByXPath("//div[@class='property-results_container']/div[contains(@class,'property-item')]");
        } catch ( IOException e ) {
            throw new ScraperException("Error connecting to Zimmo " + e.getMessage());
        }
        return results;
    }
}
