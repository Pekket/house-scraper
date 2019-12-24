package be.pekket.housescraper.provider.immovlan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ImmoVlanOwner {

    @JsonProperty("companyName")
    private String agency;
}
