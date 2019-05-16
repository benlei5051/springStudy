package com.test.website.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import static org.junit.Assert.*;

/**
 * ==========================================================================
 * RoleControllerTest
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2019/2/19 15:03
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 * ==========================================================================
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleControllerTest {
    @Autowired
    private DataSource dataSource;


    @Test
    public void show() {
        System.out.println(dataSource);
    }
}