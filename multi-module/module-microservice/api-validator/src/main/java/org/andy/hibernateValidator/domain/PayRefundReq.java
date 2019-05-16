package org.andy.hibernateValidator.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.andy.hibernateValidator.utils.RegexpUtils;
import org.andy.hibernateValidator.utils.SignatureUnion;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * ==========================================================================
 * PayRefundReq
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2019/3/28 14:22
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 * ==========================================================================
 */
@Data
public class PayRefundReq{

    @SignatureUnion("0")
    @NotBlank(message = "orderId不能为空")
    private String orderId;

    @SignatureUnion("1")
    @NotBlank(message = "sellerCode不能为空")
    @Size(max = 32, message = "sellerCode最大长度32")
    private String sellerCode;

    @NotNull(message = "tradeType不能为空")
    @Digits(integer = 2, fraction = 0, message = "tradeType最大长度2.")
    private Integer tradeType;

    @SignatureUnion("2")
    @NotBlank(message = "退款金额")
    private String refundAmount;

    @SignatureUnion("3")
    @JsonIgnore
    private String key;

    @SignatureUnion("4")
    @NotBlank(message = "deviceId不能为空")
    private String deviceId;

    @NotBlank(message = "sign不能为空.")
    private String sign;

    @Pattern(regexp = RegexpUtils.POSITIVE_NUMBER_BLANK_REGEXP, message = "page_size应为正整数")
    private String pageSize = "20";
}
