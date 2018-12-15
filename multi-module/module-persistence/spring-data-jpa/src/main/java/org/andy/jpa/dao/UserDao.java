package org.andy.jpa.dao;

import org.andy.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @title 用户DAO类
 * @describe 用户相关数据访问类
 * @author zc
 * @version 1.0 2017-09-13
 */
public interface UserDao extends JpaRepository<User, Long> {

}
