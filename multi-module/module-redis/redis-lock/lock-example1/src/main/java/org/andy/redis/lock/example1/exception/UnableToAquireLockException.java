package org.andy.redis.lock.example1.exception;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/9/28 10:57
 * @version: V1.0
 */
public class UnableToAquireLockException extends RuntimeException {
    public UnableToAquireLockException() {
    }

    public UnableToAquireLockException(String message) {
        super(message);
    }


    public UnableToAquireLockException(String message, Throwable cause) {
        super(message, cause);
    }
}
