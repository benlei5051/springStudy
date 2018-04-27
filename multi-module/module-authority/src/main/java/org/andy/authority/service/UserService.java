package org.andy.authority.service;

import org.andy.authority.dao.RoleRepository;
import org.andy.authority.dao.RoleUserRepository;
import org.andy.authority.dao.UserRepository;
import org.andy.authority.domain.Role;
import org.andy.authority.domain.RoleUser;
import org.andy.authority.domain.User;
import org.andy.authority.exception.CheckedException;
import org.andy.authority.support.BaseRepository;
import org.andy.authority.support.ResultEnum;
import org.andy.authority.vo.UserSaveVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author sean
 * 2017/11/2.
 */
@Service
@Transactional
public class UserService extends BaseServiceImpl<User, String> {
    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;
    @Resource
    private RoleUserRepository roleUserRepository;


    @Override
    public BaseRepository<User, String> getBaseDao() {
        return this.userRepository;
    }

    /**
     * 添加用户
     *
     * @param userSaveVo 前端数据
     */
    public void createUser(UserSaveVo userSaveVo, String orgCode)
            throws Exception {
        if (!userSaveVo.getOrgCode().startsWith(orgCode)) {
            throw new CheckedException(ResultEnum.ORG_NOT_EXIST);
        }
        if (!userSaveVo.getOrgCode().contains("-")) {
            throw new CheckedException(ResultEnum.ROOT_ORG_DISABLED);
        }
        User user = new User();
       /* if (findUserByLoginName(userSaveVo.getUsername()) != null) {
            throw new CheckedException(ResultEnum.USERNAME_EXIST);
        }*/
        //账户
        user.getAccount().setUsername(userSaveVo.getUsername());
        BeanUtils.copyProperties(userSaveVo, user);
        userRepository.saveAndFlush(user);
        //角色
        String[] roleIds = userSaveVo.getRoleId().split(",");
        List<Role> roles = roleRepository.findByIdIn(roleIds);
        if (roles.size() == 0) {
            throw new CheckedException(ResultEnum.DATA_NOT_EXIST);
        }
        roles.forEach(role -> createRoleAdmin(user, role));
    }

    /**
     * 建立角色账户关联
     *
     * @param user 包含角色ID的试图
     * @param role 账户实体
     */
    private void createRoleAdmin(User user, Role role) {
        RoleUser roleUser = new RoleUser();
        roleUser.setRole(role);
        roleUser.setUser(user);
        roleUserRepository.save(roleUser);
    }

    /**
     *  根据用户名查询用户
     * @param username 用户名
     * @return 用户
     */
    public User findUserByLoginName(String username) {
        return userRepository.findByAccount_Username(username);
    }
}
