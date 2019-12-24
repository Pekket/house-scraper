package be.pekket.housescraper.provider.immoweb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.util.StringUtils;

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
        String address = !StringUtils.isEmpty(this.street) ? this.street + " " : "";
        address += !StringUtils.isEmpty(this.number) ? this.number + " " : "";
        address += !StringUtils.isEmpty(this.zipcode) ? this.zipcode + " " : "";
        address += !StringUtils.isEmpty(this.city) ? this.city + " " : "";
        return address;
    }
}
