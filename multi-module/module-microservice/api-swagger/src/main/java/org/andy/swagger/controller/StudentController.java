package org.andy.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.andy.swagger.entity.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.Map;

/**
 * @author: andy
 * @Date: 2017/9/7 18:01
 * @Description:
 */
@Slf4j
@Api(value = "学生信息查询", description = "学生基本信息操作API", tags = "StudentApi", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class StudentController {

    //@ApiOperation(value = "查询学生信息", notes = "依据学生姓名查询学生信息", code = 200, response = Student.class, consumes = MediaType.APPLICATION_JSON_VALUE)
    //@ApiResponse()
    @ApiOperation(value = "getStudent", notes = "依据学生姓名查询学生信息")
    @RequestMapping(value = "student", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getStudent(@RequestParam("name") String name) {
        Student reponse = new Student();
        reponse.setId(1);
        reponse.setName("zhangsan");
        reponse.setAge(12);
        reponse.setCls("二年级");
        reponse.setAddress("重庆市大竹林");
        reponse.setSex("男");
        return reponse;
    }

    @ApiOperation(value = "addStudent", notes = "添加一个学生", code = 201)
    @RequestMapping(value = "student", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addStudent(@RequestBody Student student) {
        return;
    }


    @GetMapping(value = "/page/search")
    @ApiOperation(value = "分页查询流量赠送记录", notes = "分页查询流量赠送记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "vin", value = "车辆vin码", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "dealStatus", value = "处理状态 0：处理中，1：已处理 2：处理失败", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "phoneNumber", value = "手机号码", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "page", value = "当前页码", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "size", value = "每页大小", dataType = "int")
    })
    public void searchChangeList(@PageableDefault(sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable,
                                 @RequestParam(required = false) Integer dealStatus,
                                 @RequestParam(required = false) String vin,
                                 @RequestParam(required = false) String phoneNumber) {
        try {
            // TODO: 2018/11/19
        } catch (Exception e) {
            log.error("Invoke MnoPackageDonateController.searchChangeList exception:", e);
        }
    }


   /* @PostMapping(value = "/addPackageRecord")
    @ApiOperation(value = "新增流量赠送", notes = "新增流量赠送")
    public Map addPackageRecord(@ApiParam(value = "流量赠送参数", required = true) @Valid @RequestBody  packageDonateAddDTOList, @ApiParam(hidden = true) BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                FieldError fieldError = (FieldError) error;
                return new Status(ResultStatusCode.PARAM_ERR.getCode() + "", fieldError.getField() + fieldError.getDefaultMessage());
            }
        }
        try {
            return packageDonateService.addSimFlowGroupByApnFlag(packageDonateAddDTOList.getList());

        } catch (Exception e) {
            log.error("Invoke MnoPackageDonateController.addPackageRecord exception:", e);
            return ResultStatusCode.getMsg(e.getMessage());
        }
    }*/
}

