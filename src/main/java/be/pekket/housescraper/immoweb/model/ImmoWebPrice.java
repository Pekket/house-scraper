package be.pekket.housescraper.immoweb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ImmoWebPrice {

    @JsonProperty("mainValue")
    private String amount;
}
