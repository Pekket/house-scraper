package be.pekket.housescraper.provider.realo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class RealoImage {

    @JsonProperty("srcAt2x")
    private String url;

}
