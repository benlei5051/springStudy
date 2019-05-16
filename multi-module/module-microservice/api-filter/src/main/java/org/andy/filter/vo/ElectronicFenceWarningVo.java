package org.andy.filter.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.util.Date;

@Getter
@Setter
@ApiModel(value = "电子围栏警告VO")
public class ElectronicFenceWarningVo implements ValidatorDto {

    public interface Enter {
    }

    public interface Out {
    }


    @ApiModelProperty(value = "设备id（tbox）", required = true)
    @NotNull(groups = {Out.class}, message = "设备id不能为空")
    private String deviceId;


    @ApiModelProperty(value = "电子围栏名称")
    @NotNull
    private String fenceName;


    @ApiModelProperty(value = "电子围栏出入位置，必填项", required = true)
    @Valid
    @NotNull(groups = {Enter.class, Out.class}, message = "出入坐标不能为空")
    @ConvertGroup.List({
            @ConvertGroup(from = Enter.class, to = Default.class),
            @ConvertGroup(from = Out.class, to = Default.class)
    })
    private Position addressPosition;

    @ApiModelProperty(value = "记录时间，必填项", required = true)
    @NotNull(groups = {Enter.class}, message = "记录时间不能为空")
    private Date recordTime;

    @Override
    public void checkAndThrowErrors(Class checkType) {

    }
}
