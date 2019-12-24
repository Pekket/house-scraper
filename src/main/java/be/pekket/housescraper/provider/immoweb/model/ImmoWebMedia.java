package be.pekket.housescraper.provider.immoweb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ImmoWebMedia {

    @JsonProperty("images")
    private List<Image> images;

    public static class Image {
        @Getter
        @JsonProperty("mediumUrl")
        private String imgUrl;
    }
}
