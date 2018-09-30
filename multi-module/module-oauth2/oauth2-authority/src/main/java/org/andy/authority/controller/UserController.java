package org.andy.authority.controller;

import org.andy.authority.annoation.AccountLog;
import org.andy.authority.service.UserService;
import org.andy.authority.support.StateResult;
import org.andy.authority.util.JwtTokenUtil;
import org.andy.authority.vo.UserSaveVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户模块
 *
 * @author sean
 * 2017/11/2.
 */
@RestController
@RequestMapping("optmsurf/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;


    /**
     * 新增
     *
     * @param userSaveVo vo
     * @param request    请求
     * @return success
     */
    @PostMapping(value = "/add")
    @PreAuthorize("hasAuthority('user-opt')")
    @AccountLog(operation = "新建用户")
    public StateResult add(@RequestBody UserSaveVo userSaveVo,
                           HttpServletRequest request) throws Exception {
        String orgCode = jwtTokenUtil.getOrgCode(request);
        userService.createUser(userSaveVo, orgCode);
        return StateResult.success();
    }

}
