package org.andy.hibernateValidator.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MessageCode {

    COMMON_SUCCESS("0000_0", "执行成功"),

    COMMON_FAILURE("0000_1", "执行失败"),

    COMMON_PARAMETER_ERROR("0000_4", "参数错误"),

    COMMON_UNKNOWN_ERROR("0000_11", "未知异常");

    //Message 编码
    private String code;

    //Message 描叙
    private String message;

    MessageCode(String code) {
        this.code = code;
    }

    MessageCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    public String getMsg() {
        return message;
    }

    @JsonCreator
    public static MessageCode getStatusCode(String status) {
        for (MessageCode unit : MessageCode.values()) {
            if (unit.getCode().equals(status)) {
                return unit;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MessageCode{");
        sb.append("code='").append(code).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
