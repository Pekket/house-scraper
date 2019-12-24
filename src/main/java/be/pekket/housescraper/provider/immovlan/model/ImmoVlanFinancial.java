package be.pekket.housescraper.provider.immovlan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ImmoVlanFinancial {

    @JsonProperty("price")
    private String price;

    public String getPrice() {
        return price;
    }
}
