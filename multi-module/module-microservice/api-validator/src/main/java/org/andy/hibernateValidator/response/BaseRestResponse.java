package org.andy.hibernateValidator.response;

/**
 * ==========================================================================
 * BaseRestResponse
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2019/3/28 14:07
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 * ==========================================================================
 */
public class BaseRestResponse<T> {
    private String statusCode;
    private String statusMessage;
    private T data;

    public BaseRestResponse() {
    }

    public BaseRestResponse(String statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public BaseRestResponse(String statusCode, String statusMessage, T data) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.data = data;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
