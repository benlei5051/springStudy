package org.andy.feign.controller;

import org.andy.feign.service.CityService;
import org.andy.feign.vo.res.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: andy
 * @Date: 2018/6/7 15:07
 * @Description:
 */
@RestController
@RequestMapping("/feign/city")
public class Controller {
    @Autowired
    private CityService cityService;

    //localhost:8080/feign/city/findOne/2
    @RequestMapping(value = "/findOne/{id}", method = RequestMethod.GET)
    public City findOne(@PathVariable int id) {
        City deviceSimCardById = cityService.getDeviceSimCardById(id);
        System.out.println(deviceSimCardById.getName());
        return deviceSimCardById;
    }
//localhost:8080/feign/city/save
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public City save() {
        City city =new City();
        city.setCountry("USA");
        city.setName("andy");
        city.setState("FL");
        city.setMap("28.034468, -80.588668");
        City deviceSimCardById = cityService.save(city);
        System.out.println(deviceSimCardById.getId()+"----------------------");
        return deviceSimCardById;
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String page() {
        String byNameContainingAndCountryContainingAllIgnoringCase = cityService.findByNameContainingAndCountryContainingAllIgnoringCase("Melbourne", "Australia");
        System.out.println("----------------");
        return null;
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public City query() {
        City city= cityService.findByNameAndCountryAllIgnoringCase("Melbourne", "Australia");
        return city;
    }
}
