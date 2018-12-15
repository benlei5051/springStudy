/*------------------------------------------------------------------------------
 * GlobalExceptionHandler
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/11/21 17:41
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.beans.async.example3.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {// 此处仅用作测试

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        log.info("发生异常错误:", e);
        return e.getMessage();
    }

}
