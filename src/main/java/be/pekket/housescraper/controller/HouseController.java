package be.pekket.housescraper.controller;

import be.pekket.housescraper.model.House;
import be.pekket.housescraper.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HouseController {
    private static final String HOUSE_MAPPING = "/house";

    private static final String DEFAULT_PROVIDERS = "zimmo,immoscoop,immoweb,immovlan,tweedehands,realo";
    private static final String DEFAULT_ADDRESS_QUERY = "";

    private HouseService houseService;

    @Autowired
    public HouseController( HouseService houseService ) {
        this.houseService = houseService;
    }

    @GetMapping(value = HOUSE_MAPPING)
    public List<House> get( @RequestParam(name = "providers", defaultValue = DEFAULT_PROVIDERS, required = false) final String providers,
                            @RequestParam(name = "q", defaultValue = DEFAULT_ADDRESS_QUERY, required = false) final String addressQuery ) {
        return houseService.getLastHouses(addressQuery, providers);
    }
}