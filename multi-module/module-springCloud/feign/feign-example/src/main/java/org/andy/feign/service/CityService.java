package org.andy.feign.service;

import org.andy.feign.vo.res.City;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: andy
 * @Date: 2018/6/7 15:11
 * @Description:
 */
@FeignClient(name = "feigndbexample", url = "http://localhost:8081")
public interface CityService {

    @RequestMapping(value = "/api/cities/{id}", method = RequestMethod.GET)
    City getDeviceSimCardById(@PathVariable("id") int id);

    @RequestMapping(value = "/api/cities", method = RequestMethod.POST)
    City save(@RequestBody City city);


    @RequestMapping(value = "/api/cities/search/findByNameContainingAndCountryContainingAllIgnoringCase?name={name}&country={country}", method = RequestMethod.GET)
    String findByNameContainingAndCountryContainingAllIgnoringCase(@PathVariable("name") String name, @PathVariable("country") String country);

    @RequestMapping(value = "/api/cities/search/findByNameAndCountryAllIgnoringCase?name={name}&country={country}", method = RequestMethod.GET)
    City findByNameAndCountryAllIgnoringCase(@PathVariable("name") String name, @PathVariable("country") String country);


}
