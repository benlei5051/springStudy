package com.baomidou.mybatisplus.samples.generator.sys.service.impl;

import com.baomidou.mybatisplus.samples.generator.sys.entity.User;
import com.baomidou.mybatisplus.samples.generator.sys.mapper.UserMapper;
import com.baomidou.mybatisplus.samples.generator.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-12-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
