

### POST请求（不会返回id）
http://localhost:8081/api/cities

    {
        "name": "andytest",
        "state": "Victoria",
        "country": "Australia",
        "map": "-37.813182, 144.96292",
        "_links": {
            "self": {
                "href": "http://localhost:8081/api/cities/21"
            },
            "city": {
                "href": "http://localhost:8081/api/cities/21"
                }
            }
    }
    
### GET请求（不会返回id）
http://localhost:8081/api/cities/21

    {
        "name": "andytest",
        "state": "Victoria",
        "country": "Australia",
        "map": "-37.813182, 144.96292",
        "_links": {
            "self": {
                "href": "http://localhost:8081/api/cities/21"
            },
            "city": {
                "href": "http://localhost:8081/api/cities/21"
            }
        }
    }
    
### 排序查询（只能以显示的字段排序，没有显示的如id，就不能作为排序字段）
http://localhost:8081/api/cities?page=0&size=3&sort=name,desc
        
        {
            "_embedded": {
                "cities": [
                    {
                        "name": "Tokyo",
                        "state": "",
                        "country": "Japan",
                        "map": "35.689488, 139.691706",
                        "_links": {
                            "self": {
                                "href": "http://localhost:8081/api/cities/6"
                            },
                            "city": {
                                "href": "http://localhost:8081/api/cities/6"
                            }
                        }
                    },
                    ...此处省略
                    {
                        "name": "Barcelona",
                        "state": "Catalunya",
                        "country": "Spain",
                        "map": "41.387917, 2.169919",
                        "_links": {
                            "self": {
                                "href": "http://localhost:8081/api/cities/7"
                            },
                            "city": {
                                "href": "http://localhost:8081/api/cities/7"
                            }
                        }
                    },
                    {
                        "name": "Atlanta",
                        "state": "GA",
                        "country": "USA",
                        "map": "33.748995, -84.387982",
                        "_links": {
                            "self": {
                                "href": "http://localhost:8081/api/cities/12"
                            },
                            "city": {
                                "href": "http://localhost:8081/api/cities/12"
                            }
                        }
                    },
                    {
                        "name": "andytest",
                        "state": "Victoria",
                        "country": "Australia",
                        "map": "-37.813182, 144.96292",
                        "_links": {
                            "self": {
                                "href": "http://localhost:8081/api/cities/21"
                            },
                            "city": {
                                "href": "http://localhost:8081/api/cities/21"
                            }
                        }
                    }
                ]
            },
            "_links": {
                "first": {
                    "href": "http://localhost:8081/api/cities?page=0&size=20&sort=name,desc"
                },
                "self": {
                    "href": "http://localhost:8081/api/cities"
                },
                "next": {
                    "href": "http://localhost:8081/api/cities?page=1&size=20&sort=name,desc"
                },
                "last": {
                    "href": "http://localhost:8081/api/cities?page=1&size=20&sort=name,desc"
                },
                "profile": {
                    "href": "http://localhost:8081/api/profile/cities"
                },
                "search": {
                    "href": "http://localhost:8081/api/cities/search"
                }
            },
            "page": {
                "size": 20,
                "totalElements": 21,
                "totalPages": 2,
                "number": 0
            }
        }
        
###  查询用List<Object[]>封装        
        
        @Query(nativeQuery = true, value = "select s.sid,s.device_id,s.iccid,s.msisdn,s.sim_status,s.active_time,s.create_date, " +
	        "'' as group_device,'' as device_type,'' as vin   " +
	        "from base_device_sim_card s left join base_device_fittings f on s.device_id = f.fittings_id and f.del_flag = 0  " +
	        "where s.del_flag = 0  " +
	        "and s.project_id in ?4 " +
            "and (?1 is null or ?1 = '' or s.device_id = ?1)  " +
            "and (?2 is null or ?2 = '' or s.iccid = ?2)  " +
            "and (?3 is null or ?3 = '' or s.msisdn = ?3) " +
	        "group by s.device_id " +
	        "order by s.create_date desc limit ?5, ?6")
	    public List<Object[]> searchPage(String deviceId, String iccid, String msisdn, Set<String> projectId, int startline, int size);

### 自定义的查询，路径后面一定要加上search
//请求路径 http://localhost:8081/api/cities/search/findByNameAndCountryAllIgnoringCase?name=Melbourne&country=Australia
    
    @Query("select c from City c where c.name like :name and c.country like :country")
    	City findByNameAndCountryAllIgnoringCase(@Param("name") String name,
                                                 @Param("country") String country);

