package org.andy.standard.exception;

/**
 * ==========================================================================
 * MessageException
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2019/2/15 10:39
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 * ==========================================================================
 */
public class MessageException extends RuntimeException {

    public MessageException() {
        super();
    }

    public MessageException(String message) {
        super(message);
    }

    public MessageException(Throwable cause) {
        super(cause);
    }

    public MessageException(String message, Throwable cause) {
        super(message, cause);
    }


}
