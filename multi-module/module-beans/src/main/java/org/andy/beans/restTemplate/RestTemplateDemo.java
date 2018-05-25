package org.andy.beans.restTemplate;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author: andy
 * @Date: 2017/9/25 14:59
 * @Description:
 */
@SpringBootApplication
public class RestTemplateDemo {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RestTemplateDemo.class);
    }

    /*@Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.build();
    }*/

    @Bean
    public CommandLineRunner commandLineRunner(RestTemplate restTemplate) {
        return args -> {
            String url = "https://free-api.heweather.com/v5/forecast?city=CN101080101&key=5c043b56de9f4371b0c7f8bee8f5b75e";
            String resp = restTemplate.getForObject(url, String.class);
            System.out.println(resp);
            System.out.println("-------");
        };
    }
}
