package org.andy.hibernateValidator.domain;

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
public class Position {

    @Range(min = -180, max = 180, message = "经度范围必须在[{min} ~ {max}]之间")
    private BigDecimal lon;

    @Range(min = -90, max = 90, message = "纬度范围必须在[{min} ~ {max}]之间")
    private BigDecimal lat;
}
