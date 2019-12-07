package be.pekket.housescraper.immoweb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ImmoWebProperty {

    @JsonProperty("title")
    private String title;
    @JsonProperty("location")
    private ImmoWebAddress address;
}
