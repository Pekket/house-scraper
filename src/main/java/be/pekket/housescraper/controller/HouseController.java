package be.pekket.housescraper.controller;

import be.pekket.housescraper.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HouseController {
    private static final String HOUSE_MAPPING = "/house/init";

    private HouseService houseService;

    @Autowired
    public HouseController( HouseService houseService ) {
        this.houseService = houseService;
    }

    @GetMapping(value = HOUSE_MAPPING)
    public void init() {
        houseService.processHouses();
    }
}