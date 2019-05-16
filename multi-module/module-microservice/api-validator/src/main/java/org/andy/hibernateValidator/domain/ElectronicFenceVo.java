package org.andy.hibernateValidator.domain;

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
public class ElectronicFenceVo {

    public interface Add {
    }

    public interface Edit {
    }

    @NotNull(groups = {Edit.class}, message = "围栏sid不能为空")
    private Long sid;

    private Long uid;

//    @NotBlank(message = "uaid不能为空")
    private String uaid;

    @NotBlank(groups = {Add.class}, message = "车辆vin号不能为空")
    @Size(min = 1, max = 20, groups = {Add.class}, message = "车辆vin号长度必须在[{min} ~ {max}]之间")
    private String vin;

    @NotBlank(groups = {Add.class, Edit.class}, message = "电子围栏名称不能为空")
    @Size(min = 1, max = 10, groups = {Add.class, Edit.class}, message = "电子围栏名称长度必须在[{min} ~ {max}]之间")
    private String fenceName;

    @NotBlank(groups = {Add.class, Edit.class}, message = "中心位置名称不能为空")
    @Size(min = 1, max = 128, groups = {Add.class, Edit.class}, message = "中心位置名称长度必须在[{min} ~ {max}]之间")
    private String cpName;

    @Valid
//    @NotNull(groups = {Add.class, Edit.class}, message = "中心位置不能为空")
//    @ConvertGroup.List({
//            @ConvertGroup(from = Add.class, to = Default.class),
//            @ConvertGroup(from = Edit.class, to = Default.class)
//    })
    private Position centralPosition;

    @NotNull(groups = {Add.class, Edit.class}, message = "围栏半径不能为空")
    @Range(min = 1, max = 200, groups = {Add.class, Edit.class}, message = "围栏半径长度必须在[{min} ~ {max}]之间")
    private Integer radius;

    private Integer status;

    private String projectId;

}
