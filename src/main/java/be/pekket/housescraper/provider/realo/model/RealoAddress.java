package be.pekket.housescraper.provider.realo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class RealoAddress {

    @JsonProperty("id")
    private String id;
    @JsonProperty("street")
    private String street;
    @JsonProperty("number")
    private String number;
    @JsonProperty("postalCode")
    private String postalCode;
    @JsonProperty("locality")
    private String locality;

    public String toString() {
        String address = !StringUtils.isEmpty(this.street) ? this.street + " " : "";
        address += !StringUtils.isEmpty(this.number) ? this.number + " " : "";
        address += !StringUtils.isEmpty(this.postalCode) ? this.postalCode + " " : "";
        address += !StringUtils.isEmpty(this.locality) ? this.locality : "";
        return address;
    }
}
