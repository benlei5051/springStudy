/*------------------------------------------------------------------------------
 * CustomAsyncRequestTimeoutException
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/11/21 14:04
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.beans.async.example1.exception;

/**
 * 自定义超时异常类
 * @author oKong
 *
 */
public class CustomAsyncRequestTimeoutException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 8754629185999484614L;

    public CustomAsyncRequestTimeoutException(String uri){
        super(uri);
    }
}