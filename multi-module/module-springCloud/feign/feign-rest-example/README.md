#### 用feign直接调用dbexample模块中的repository的方法时
新增，修改，根据id查询都可以直接用对象接收

    @RequestMapping(value = "/api/cities/{id}", method = RequestMethod.GET)
    City getDeviceSimCardById(@PathVariable("id") int id);
    
    @RequestMapping(value = "/api/cities", method = RequestMethod.POST)
    City save(@RequestBody City city);
    
    
### 用feign请求，返回体可以用Map或者String接收

    @RequestMapping(value = "/api/cities/search/findByNameContainingAndCountryContainingAllIgnoringCase?name={name}&country={country}", method = RequestMethod.GET)
    String findByNameContainingAndCountryContainingAllIgnoringCase(@PathVariable("name") String name,@PathVariable("country") String country);
    
    @RequestMapping(value = "/api/cities/search/findByNameContainingAndCountryContainingAllIgnoringCase?name={name}&country={country}", method = RequestMethod.GET)
    Map<String,Object> findByNameContainingAndCountryContainingAllIgnoringCase(@PathVariable("name") String name,@PathVariable("country") String country);
