/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.data.rest.service;

import org.springframework.data.jpa.repository.Query;
import sample.data.rest.domain.City;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "cities", path = "cities")
interface CityRepository extends PagingAndSortingRepository<City, Long> {



//请求路径 http://localhost:8081/api/cities/search/findByNameContainingAndCountryContainingAllIgnoringCase?name=Melbourne&country=Australia&page=0&size=3
	Page<City> findByNameContainingAndCountryContainingAllIgnoringCase(
            @Param("name") String name, @Param("country") String country,
            Pageable pageable);

//请求路径 http://localhost:8081/api/cities/search/findByNameAndCountryAllIgnoringCase?name=Melbourne&country=Australia
	@Query("select c from City c where c.name like :name and c.country like :country")
	City findByNameAndCountryAllIgnoringCase(@Param("name") String name,
                                             @Param("country") String country);

}
