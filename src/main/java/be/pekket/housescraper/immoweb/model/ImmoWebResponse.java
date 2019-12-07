package be.pekket.housescraper.immoweb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ImmoWebResponse {

    @JsonProperty("results")
    private List<ImmoWebHouse> houses;
}