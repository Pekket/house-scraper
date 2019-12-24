package be.pekket.housescraper.provider.immovlan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ImmoVlanCity {

    @JsonProperty("zipCode")
    private String zipCode;
    @JsonProperty("labels")
    private List<Label> labels;


    public static class Label {
        @Getter
        @JsonProperty("label")
        private String label;
     }
}
