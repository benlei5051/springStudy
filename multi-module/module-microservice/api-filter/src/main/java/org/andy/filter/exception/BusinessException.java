package org.andy.filter.exception;

import lombok.Getter;
import org.andy.filter.vo.ResultStatusCode;

/**
 * @author wiiyaya
 * @date 2018/11/29.
 */
@Getter
public class BusinessException extends Exception {
    private static final long serialVersionUID = -2451413487804514298L;

    private ResultStatusCode resultStatusCode;

    public BusinessException(ResultStatusCode resultStatusCode) {
        super("[" + resultStatusCode.getCode() + "|" + resultStatusCode.getMsg() + "]");
        this.resultStatusCode = resultStatusCode;
    }
}
