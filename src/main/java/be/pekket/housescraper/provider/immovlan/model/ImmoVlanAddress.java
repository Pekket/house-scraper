package be.pekket.housescraper.provider.immovlan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ImmoVlanAddress {

    @JsonProperty("street")
    private String street;
    @JsonProperty("number")
    private String number;
    @JsonProperty("city")
    private ImmoVlanCity city;

    public String toString() {
        String address = "";
        address += this.getStreet() + " ";
        address += this.getNumber() + " ";
        address += this.getCity().getZipCode() + " ";
        address += this.getCity().getLabels().get(0).getLabel();
        return address;
    }
}

