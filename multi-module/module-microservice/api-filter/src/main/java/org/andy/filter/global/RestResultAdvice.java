package org.andy.filter.global;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.andy.filter.vo.ResultData;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;

/**
 * @author wiiyaya
 * @date 2018/11/29.
 */

@Order(1)
@ControllerAdvice(basePackages = "org.andy.filter.controller.ElectronicFenceWarningController")
@Slf4j
public class RestResultAdvice implements ResponseBodyAdvice<Object> {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        //获取当前处理请求的controller的方法
        String methodName=methodParameter.getMethod().getName();
        // 不拦截/不需要处理返回值 的方法
//        String method= "loginCheck"; //如登录
//        //不拦截
//        return !method.equals(methodName);
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.info("请求返回数据类型class={}", body.getClass().getName());
        response.getHeaders().setContentType(MediaType.TEXT_PLAIN);
        String s = JSON.toJSONString(body);
        return "3232312";
//        response.getHeaders().setContentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE));
//        response.getHeaders().setContentType(MediaType.parseMediaType(MediaType.TEXT_XML_VALUE));
//        if (selectedConverterType == StringHttpMessageConverter.class) {
//            try {
//                return objectMapper.writeValueAsString(ResultData.success(body));
//            } catch (JsonProcessingException e) {
//                return body;
//            }
//        } else {
//            return ResultData.success(body);
//        }
    }
}
