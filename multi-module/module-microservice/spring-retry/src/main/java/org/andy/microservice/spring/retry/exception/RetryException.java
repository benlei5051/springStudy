package org.andy.microservice.spring.retry.exception;

import lombok.Builder;
import lombok.Getter;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/10/15 17:10
 * @version: V1.0
 */
@Builder
@Getter
public class RetryException extends RuntimeException {
    private String code;
    private String message;
}
