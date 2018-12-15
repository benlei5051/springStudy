/*------------------------------------------------------------------------------
 * AuditorAwareImpl
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/13 15:19
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.jpa.service;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component("auditorAware")
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = servletRequestAttributes.getRequest();
        return "andy";
    }
}

