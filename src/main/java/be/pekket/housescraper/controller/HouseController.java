package be.pekket.housescraper.controller;

import be.pekket.housescraper.immovlan.service.ImmoVlanService;
import be.pekket.housescraper.model.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HouseController {
    private static final String HOUSE_MAPPING = "/house/init";

    private ImmoVlanService immoVlanService;

    @Autowired
    public HouseController( ImmoVlanService immoVlanService) {
        this.immoVlanService = immoVlanService;
    }

    @GetMapping(value = HOUSE_MAPPING)
    public List<House> init() {
        //For testing purposes
        return immoVlanService.search();
    }
}