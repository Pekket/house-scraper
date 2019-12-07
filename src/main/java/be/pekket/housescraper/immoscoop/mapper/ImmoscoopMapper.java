package be.pekket.housescraper.immoscoop.mapper;

import be.pekket.housescraper.model.House;
import com.gargoylesoftware.htmlunit.html.DomNode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.List;

@Service
public class ImmoscoopMapper {

    private static final String TITLE_XPATH = ".//h5[@class='search-result-title']";
    private static final String IMG_XPATH = ".//a[@class='search-result-img-wrapper']";
    private static final String ADDRESS_XPATH = ".//span[@class='search-result-info center-block']";


    public List<House> map( List<DomNode> elements ) {
        List<House> houses = new LinkedList<>();

        for ( DomNode domElement : elements ) {
            House house = House.builder()
                    .timestamp(System.currentTimeMillis())
                    .title(getContent(domElement, TITLE_XPATH))
                    .price("Onbekend")
                    .address(getContent(domElement, ADDRESS_XPATH))
                    .url(getImgUrl(domElement, IMG_XPATH))
                    .imgUrl(getUrl(domElement, IMG_XPATH))
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
            }
        }
        return StringUtils.trimWhitespace(content);
    }

    private String getImgUrl( DomNode domNode, String xpath ) {
        String url = null;
        DomNode node = domNode.getFirstByXPath(xpath);
        if ( node != null && node.getAttributes().getNamedItem("href") != null ) {
            url = node.getAttributes().getNamedItem("href").getTextContent();
        }
        return StringUtils.trimWhitespace(url);
    }

    private String getUrl( DomNode domNode, String xpath ) {
        String url = null;
        DomNode node = domNode.getFirstByXPath(xpath);
        if ( node != null && node.getAttributes().getNamedItem("style") != null ) {
            url = node.getAttributes().getNamedItem("style").getTextContent();
            url = url.substring(22, url.length() - 2);
        }
        return StringUtils.trimWhitespace(url);
    }

}
