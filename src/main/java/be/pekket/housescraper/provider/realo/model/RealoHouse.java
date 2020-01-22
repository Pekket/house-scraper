package be.pekket.housescraper.provider.realo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class RealoHouse {

    @JsonProperty("id")
    private String id;
    @JsonProperty("price")
    private String price;
    @JsonProperty("address")
    private RealoAddress address;
    @JsonProperty("listingPictureUrlsFirst")
    private RealoImage image;

    public String getUrl() {
        String url = "https://www.realo.be/en/";
        url += address.toString().replace(" ", "-");
        url += "/" + address.getId();
        url += "?l=" + this.getId();
        return url;
    }
}
