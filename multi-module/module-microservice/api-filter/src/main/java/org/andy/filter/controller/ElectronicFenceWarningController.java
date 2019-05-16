package org.andy.filter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.andy.filter.exception.BusinessException;
import org.andy.filter.vo.ElectronicFenceWarningVo;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wiiyaya
 * @date 2018/12/3.
 */
@Api(value = "ElectronicFenceWarning", description = "电子围栏警告")
@RestController
@RequestMapping("/electronic/fence")
public class ElectronicFenceWarningController {

    @ApiOperation(value = "电子围栏警告历史记录", httpMethod = "GET", response = ElectronicFenceWarningVo.class, notes = "statusCode = 0 返回结果将封装为：1.有数据{rows:[{}, {}], total: 10}，2.无数据{rows:[], total: 0}；statusCode = 其他，结果将封装为：空 null")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vin", value = "车辆vin码", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "page", value = "页数从1开始", defaultValue = "1", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "size", value = "每页数量", defaultValue = "10", paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/warning/page", method = RequestMethod.GET)
    public ElectronicFenceWarningVo warningPage(@RequestParam @NotBlank String vin, Pageable pageable){
        ElectronicFenceWarningVo vo = new ElectronicFenceWarningVo();
        vo.setDeviceId("bbbb");
        return vo;
    }

    @ApiOperation(value = "电子围栏警告驶入", httpMethod = "POST", notes = "statusCode = 0 返回结果将封装为：空 null；statusCode = 其他，结果将封装为：空 null")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "warningVo", value = "电子围栏警告信息", required = true, paramType = "body", dataType = "电子围栏警告VO")
    })
    @RequestMapping(value = "/warning/enter", method = RequestMethod.POST)
    public String warningEnter(@RequestBody @Validated(ElectronicFenceWarningVo.Enter.class) ElectronicFenceWarningVo warningVo){
        return "test";
    }

}
