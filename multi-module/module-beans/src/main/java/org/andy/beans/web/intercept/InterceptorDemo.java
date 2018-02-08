package org.andy.beans.web.intercept;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: andy
 * @Date: 2018/2/8 16:09
 * @Description:
 */
@Component("interceptorDemo")
public class InterceptorDemo implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
        System.out.println(">>>HelloInterceptor.preHandle>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        System.out.println(">>>HelloInterceptor.postHandle>>>>>>>");

    }

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        System.out.println(">>>HelloInterceptor.afterCompletion>>>>>>>");
    }
}
