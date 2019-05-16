package org.andy.filter.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

@Getter
@Setter
@ApiModel(value = "电子围栏VO")
public class ElectronicFenceVo implements ValidatorDto {

    public interface Add {
    }

    public interface Edit {
    }

    @ApiModelProperty(value = "围栏sid，修改时必填项")
    @NotNull(groups = {Edit.class}, message = "围栏sid不能为空")
    private Long sid;

    @ApiModelProperty(value = "当前用户uid/sid", hidden = true)
    private Long uid;

    @ApiModelProperty(value = "当前用户aid/accountId", hidden = true)
    private String uaid;

    @ApiModelProperty(value = "车辆vin号，新增时必填项", example = "LNBSCCAH7JT030167")
    @NotBlank(groups = {Add.class}, message = "车辆vin号不能为空")
    @Size(min = 1, max = 20, groups = {Add.class}, message = "车辆vin号长度必须在[{min} ~ {max}]之间")
    private String vin;

    @ApiModelProperty(value = "电子围栏名称，必填项", required = true, example = "在武汉的围栏")
    @NotBlank(groups = {Add.class, Edit.class}, message = "电子围栏名称不能为空")
    @Size(min = 1, max = 10, groups = {Add.class, Edit.class}, message = "电子围栏名称长度必须在[{min} ~ {max}]之间")
    private String fenceName;

    @ApiModelProperty(value = "电子围栏中心位置名称，必填项", required = true, example = "武汉市沌口区东风公司")
    @NotBlank(groups = {Add.class, Edit.class}, message = "中心位置名称不能为空")
    @Size(min = 1, max = 128, groups = {Add.class, Edit.class}, message = "中心位置名称长度必须在[{min} ~ {max}]之间")
    private String cpName;

    @ApiModelProperty(value = "电子围栏中心位置，必填项", required = true)
    @Valid
    @NotNull(groups = {Add.class, Edit.class}, message = "中心位置不能为空")
    @ConvertGroup.List({
            @ConvertGroup(from = Add.class, to = Default.class),
            @ConvertGroup(from = Edit.class, to = Default.class)
    })
    private Position centralPosition;

    @ApiModelProperty(value = "电子围栏半径，必填项", required = true, example = "1")
    @NotNull(groups = {Add.class, Edit.class}, message = "围栏半径不能为空")
    @Range(min = 1, max = 200, groups = {Add.class, Edit.class}, message = "围栏半径长度必须在[{min} ~ {max}]之间")
    private Integer radius;

    @ApiModelProperty(value = "电子围栏生效标识  1：生效, 0:不生效")
    private Integer status;

    @ApiModelProperty(value = "项目", hidden = true)
    private String projectId;

    @Override
    public void checkAndThrowErrors(Class checkType) {

    }
}
