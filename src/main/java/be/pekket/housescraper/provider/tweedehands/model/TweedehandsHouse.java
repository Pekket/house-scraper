package be.pekket.housescraper.provider.tweedehands.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TweedehandsHouse {

    @JsonProperty("itemId")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("priceInfo")
    private TweedehandsPrice priceInfo;
    @JsonProperty("vipUrl")
    private String url;
    @JsonProperty("imageUrls")
    private String[] imgUrls;
    @JsonProperty("sellerInformation")
    private TweedehandsSellerInformation sellerInformation;
}
