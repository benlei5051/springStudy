/*------------------------------------------------------------------------------
 * TimeoutCallableProcessor
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/11/21 13:52
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.beans.async.example1.config;

import org.andy.beans.async.example1.exception.CustomAsyncRequestTimeoutException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.CallableProcessingInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;

public class TimeoutCallableProcessor extends CallableProcessingInterceptorAdapter {
    @Override
    public <T> Object handleTimeout(NativeWebRequest request, Callable<T> task) throws Exception {
        HttpServletRequest httpRequest = request.getNativeRequest(HttpServletRequest.class);
        //记录超时的url地址
        return new CustomAsyncRequestTimeoutException(httpRequest.getRequestURI());
    }
}
