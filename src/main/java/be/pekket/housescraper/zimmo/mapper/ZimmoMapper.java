package be.pekket.housescraper.zimmo.mapper;

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

    public List<House> map( List<DomNode> elements ) {
        List<House> houses = new LinkedList<>();

        for ( DomNode domElement : elements ) {
            House.Builder house = new House.Builder();

            house.title(getContent(domElement, TITLE_XPATH));
            house.price(getContent(domElement, PRICE_XPATH));
            house.address(getContent(domElement, ADDRESS_XPATH));
            house.url(getLinkUrl(domElement, URL_XPATH));

            houses.add(house.build());
        }

        return houses;
    }

    private String getContent(DomNode domNode, String xpath) {
        String content = null;
        DomNode node = (DomNode) domNode.getFirstByXPath(xpath);
        if(node != null) {
            content =  node.getTextContent();
            if(!StringUtils.isEmpty(content)) {
                content = content.replaceAll("\\n\\W*\\n\\W*", " ");
                content = content.replaceAll("€\\W+", "");
            }
        }
        return StringUtils.trimWhitespace(content);
    }

    private String getLinkUrl(DomNode domNode, String xpath) {
        String url = null;
        DomNode node = (DomNode) domNode.getFirstByXPath(xpath);
        if(node != null && node.getAttributes().getNamedItem("href") != null) {
            url = ZIMMO_BASE_URL + node.getAttributes().getNamedItem("href").getTextContent();
        }
        return StringUtils.trimWhitespace(url);
    }
}
