package org.andy.hystrix.ribbon.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/10/9 15:21
 * @version: V1.0
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;


    /**
     *
     * Hystrix的默认超时时间是1秒。默认开启超时机制。如需关闭Hystrix的超时，可将xxx.enabled设置为false。
     *
     * 方法上的超时配置覆盖了application配置文件的超时配置
     *
     * 指定HystrixCommandKey，值为test，即走test配置的超时
     *
     * @param name
     * @return
     */
    /*@HystrixCommand(fallbackMethod = "hiError", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "15000")
    })*/
    @HystrixCommand(fallbackMethod = "hiError", commandKey = "test")
    public String hiService(String name) {
        return restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);
    }

    /**
     * 未指定HystrixCommandKey，即走default配置的超时
     * @return
     */
    @HystrixCommand(fallbackMethod = "timeOutHandle")
    public String testTimeOut() {
        return restTemplate.getForObject("http://SERVICE-HI/dc", String.class);
    }

    public String hiError(String name) {
        return "hi," + name + ",sorry,error!";
    }
    public String timeOutHandle() {
        return "service error，please try again";
    }
}


