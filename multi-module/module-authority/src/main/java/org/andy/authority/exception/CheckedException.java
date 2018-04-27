package org.andy.authority.exception;

import org.andy.authority.support.ResultEnum;

/**
 * Created by sean on 2018/1/8.
 */
public class CheckedException extends RuntimeException {
    private static final long serialVersionUID = -6112780192479692859L;

    private String code;

    private String message;

    public CheckedException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }

    public CheckedException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
