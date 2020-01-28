package be.pekket.housescraper.provider.immovlan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class ImmoVlanHouse implements Serializable {

    @JsonProperty("displayTitle")
    private String title;
    @JsonProperty("financialInfo")
    private ImmoVlanFinancial financial;
    @JsonProperty("ownerInfo")
    private ImmoVlanOwner owner;
    @JsonProperty("displayUrl")
    private String url;
    @JsonProperty("displayImgUrl")
    private String imgUrl;
}
