package be.pekket.housescraper.provider.tweedehands.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class TweedehandsModel {

    @JsonProperty("listings")
    private List<TweedehandsHouse> houses;
}
