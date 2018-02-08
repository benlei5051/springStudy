package org.andy.beans.web.intercept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author: andy
 * @Date: 2018/2/8 16:13
 * @Description:
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    private InterceptorDemo interceptorDemo;

    @Autowired
    InterceptorConfig(InterceptorDemo interceptorDemo){
        this.interceptorDemo=interceptorDemo;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptorDemo).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
