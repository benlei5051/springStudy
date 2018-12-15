package org.andy.jpa.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.andy.jpa.dao.OperationLogDao;
import org.andy.jpa.dao.UserDao;
import org.andy.jpa.entity.OperationLog;
import org.andy.jpa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author zc
 * @version 1.0 2017-09-13
 * @title 业务服务
 * @describe 演示
 */
@Service
public class DemoService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private OperationLogDao operationLogDao;

    @Transactional(rollbackFor = Exception.class)
    public void addUser(String name) {
        OperationLog log = new OperationLog();
        log.setContent("create user:" + name);
        operationLogDao.save(log);

//        User user = new User();
//        user.setName(name);
//        userDao.save(user);
    }

    public void test1() throws JsonProcessingException {

        OperationLog operationLog = operationLogDao.findOne(1L);
        operationLog.setContent("wendy222");
        operationLog.setCreateTime(new Date());
        operationLogDao.save(operationLog);

    }

}
