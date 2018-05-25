package org.andy.beans.restTemplate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author: andy
 * @Date: 2018/5/23 21:02
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTemplateDemoTest {

    private  RestTemplate restTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Before
    public void TestRestTemplateGenerator() {
        this.restTemplate = restTemplateBuilder.build();
    }


    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("https://free-api.heweather.com/v5/forecast?city=CN101080101&key=5c043b56de9f4371b0c7f8bee8f5b75e", String.class);
        System.out.println(response.getBody());
    }


}