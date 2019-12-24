package be.pekket.housescraper.provider.zimmo.mapper;

import be.pekket.housescraper.provider.Provider;
import be.pekket.housescraper.model.House;
import com.gargoylesoftware.htmlunit.html.DomNode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.List;

@Service
public class ZimmoMapper {

    private static final String ZIMMO_BASE_URL = "https://www.zimmo.be";

    private static final String PRICE_XPATH = "div[@class='property-item_price ']";
    private static final String ADDRESS_XPATH = "div[@class='property-item_address']";
    private static final String TITLE_XPATH = "div[@class='property-item_title ']/a";
    private static final String URL_XPATH = "a[@class='property-item_link']";
    private static final String IMG_XPATH = "a[@class='property-item_link']/div[@class='property-item-slider']/img";

    public List<House> map( List<DomNode> elements ) {
        List<House> houses = new LinkedList<>();

        for ( DomNode domElement : elements ) {
            House house = House.builder()
                    .timestamp(System.currentTimeMillis())
                    .provider(Provider.ZIMMO)
                    .title(this.getContent(domElement, TITLE_XPATH))
                    .price(this.getContent(domElement, PRICE_XPATH))
                    .address(this.getContent(domElement, ADDRESS_XPATH))
                    .url(this.getLinkUrl(domElement, URL_XPATH))
                    .imgUrl(this.getImgUrl(domElement, IMG_XPATH))
                    .build();

            houses.add(house);
        }

        return houses;
    }

    private String getContent( DomNode domNode, String xpath ) {
        String content = null;
        DomNode node = domNode.getFirstByXPath(xpath);
        if ( node != null ) {
            content = node.getTextContent();
            if ( !StringUtils.isEmpty(content) ) {
                content = content.replaceAll("\\n\\W*\\n\\W*", " ");
                content = content.replaceAll("€\\W+", "");
            }
        }
        return StringUtils.trimWhitespace(content);
    }

    private String getLinkUrl( DomNode domNode, String xpath ) {
        String url = null;
        DomNode node = domNode.getFirstByXPath(xpath);
        if ( node != null && node.getAttributes().getNamedItem("href") != null ) {
            url = ZIMMO_BASE_URL + node.getAttributes().getNamedItem("href").getTextContent();
        }
        return StringUtils.trimWhitespace(url);
    }

    private String getImgUrl( DomNode domNode, String xpath ) {
        String url = null;
        DomNode node = domNode.getFirstByXPath(xpath);
        if ( node != null && node.getAttributes().getNamedItem("src") != null ) {
            url = node.getAttributes().getNamedItem("src").getTextContent();
        }
        return StringUtils.trimWhitespace(url);
    }
}
