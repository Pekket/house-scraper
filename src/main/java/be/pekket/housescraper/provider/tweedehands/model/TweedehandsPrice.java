package be.pekket.housescraper.provider.tweedehands.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TweedehandsPrice {

    @JsonProperty("priceCents")
    private String price;
}
