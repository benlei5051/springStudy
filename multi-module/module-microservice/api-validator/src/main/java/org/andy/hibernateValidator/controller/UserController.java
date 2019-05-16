package org.andy.hibernateValidator.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.andy.hibernateValidator.domain.ElectronicFenceVo;
import org.andy.hibernateValidator.domain.PayRefundReq;
import org.andy.hibernateValidator.domain.UserInfo;
import org.andy.hibernateValidator.response.BaseRestResponse;
import org.andy.hibernateValidator.utils.SignatureUtil;
import org.apache.catalina.User;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import util.AccountLog;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.groups.Default;
import java.util.Date;

/**
 *
 *
 *
 *   swagger 地址   http://localhost:8083/hibernate-service/services/swagger-ui.html#!/UserController/refundUsingPOST
 * @author: andy
 * @Date: 2017/9/6 14:40
 * @Description:
 */
@RestController
@RequestMapping(value = "/users")
@Api(description = "测试swagger运行API", tags = "UserController")
public class UserController {
//访问路径   localhost:8082/hibernate-service/services/users/register

//    @JsonView(UserInfo.ProjectDetailView.class)
    @JsonView(UserInfo.ProjectDetailView.class)
//    @AccountLog(operation = "测试切面方法")
    @PostMapping(value = "/register")
    public UserInfo register(@RequestBody @Valid UserInfo userInfo) {
        userInfo.setBirthday(new Date());
        return userInfo;
    }

//localhost:8083/hibernate-service/services/users/refund
//    {
//        "orderId": "123",
//            "sellerCode": "sdfdf",
//            "tradeType": 12,
//            "refundAmount": "500",
//            "deviceId": "PDSN200135",
//            "sign": "DFSFSDFSDFEFWFCX",
//            "pageSize":"2r"
//    }
    @RequestMapping(value = "/refund", method = { RequestMethod.POST })
    public BaseRestResponse refund(@RequestBody @Valid PayRefundReq payRefundReq,
                                   HttpServletRequest request) {
        String sign = SignatureUtil.getSign(payRefundReq);
        return new BaseRestResponse("0", "success", sign);
    }


    @ApiOperation(value = "新增电子围栏", httpMethod = "POST", response = Long.class, notes = "statusCode = 0 返回结果将封装为：1.电子围栏sid；statusCode = 其他，结果将封装为：空 null")

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Long fenceAdd(@ApiParam(value = "电子围栏配置信息", required = true) @RequestBody @Validated(value = {ElectronicFenceVo.Add.class}) ElectronicFenceVo electronicFenceVo)  {
        String projectId = electronicFenceVo.getProjectId();
        return 10L;
    }

    @ApiOperation(value = "修改电子围栏", httpMethod = "POST", notes = "statusCode = 0 返回结果将封装为：空 null；statusCode = 其他，结果将封装为：空 null")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void fenceEdit(@ApiParam(value = "电子围栏配置信息", required = true) @RequestBody @Validated(ElectronicFenceVo.Edit.class) ElectronicFenceVo electronicFenceVo){
        String projectId = electronicFenceVo.getProjectId();
        System.out.println(projectId);
    }
}



