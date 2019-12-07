package be.pekket.housescraper.immoweb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ImmoWebAddress {

    @JsonProperty("street")
    private String street;
    @JsonProperty("number")
    private String number;
    @JsonProperty("locality")
    private String city;
    @JsonProperty("postalCode")
    private String zipcode;


    public String toString() {
        return this.street + " " + this.number + " " + this.zipcode + " " + this.city;
    }
}
