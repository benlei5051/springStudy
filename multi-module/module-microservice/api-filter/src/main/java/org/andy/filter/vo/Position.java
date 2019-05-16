package org.andy.filter.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "地理位置")
public class Position {

    @ApiModelProperty(value = "经度，必填项", required = true)
    @Range(min = -180, max = 180, message = "经度范围必须在[{min} ~ {max}]之间")
    private BigDecimal lon;

    @ApiModelProperty(value = "纬度，必填项", required = true)
    @Range(min = -90, max = 90, message = "纬度范围必须在[{min} ~ {max}]之间")
    private BigDecimal lat;
}
