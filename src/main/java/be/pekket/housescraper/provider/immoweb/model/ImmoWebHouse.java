package be.pekket.housescraper.provider.immoweb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ImmoWebHouse {

    @JsonProperty("id")
    private String id;
    @JsonProperty("customerName")
    private String agency;
    @JsonProperty("property")
    private ImmoWebProperty property;
    @JsonProperty("media")
    private ImmoWebMedia media;
    @JsonProperty("price")
    private ImmoWebPrice price;


}
