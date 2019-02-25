package org.andy.datasource;

import org.andy.datasource.domain.p.User;
import org.andy.datasource.repository.p.UserRepository;
import org.andy.datasource.domain.s.Message;
import org.andy.datasource.repository.s.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * ==========================================================================
 * DataSourceApplicationTest
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2019/2/13 16:16
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 * ==========================================================================
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceApplicationTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Test
    @Transactional
    public void test() throws Exception {

        userRepository.save(new User("aaa", 10));
        userRepository.save(new User("bbb", 20));
        userRepository.save(new User("ccc", 30));
        userRepository.save(new User("ddd", 40));
        userRepository.save(new User("eee", 50));
        int a = 1/0;
//        Assert.assertEquals(5, userRepository.findAll().size());

        messageRepository.save(new Message("o1", "aaaaaaaaaa"));
        messageRepository.save(new Message("o2", "bbbbbbbbbb"));
        messageRepository.save(new Message("o3", "cccccccccc"));

//        Assert.assertEquals(3, messageRepository.findAll().size());

    }

//    @Test
//    public void testFind() {
//        User byId = userService.getById(1L);
//        System.out.println(byId.getName());
//    }
}