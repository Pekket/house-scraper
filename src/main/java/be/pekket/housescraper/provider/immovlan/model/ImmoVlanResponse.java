package be.pekket.housescraper.provider.immovlan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ImmoVlanResponse {

    @JsonProperty("items")
    private List<ImmoVlanHouse> houses;
}
