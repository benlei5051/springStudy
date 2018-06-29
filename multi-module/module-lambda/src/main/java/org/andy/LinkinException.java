package org.andy;

/**
 * @author: andy
 * @Date: 2018/6/22 10:42
 * @Description:
 */
public class LinkinException extends RuntimeException {
    /**
     * 不带异常信息
     */
    public LinkinException() {


    }


    /**
     * @param msg 带异常信息
     */
    public LinkinException(String msg) {
        super(msg);
    }


    public String getMessage() {
        return "No value present in the Optional instance";
    }


}