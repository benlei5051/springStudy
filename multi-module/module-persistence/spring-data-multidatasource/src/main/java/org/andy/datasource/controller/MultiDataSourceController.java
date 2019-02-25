package org.andy.datasource.controller;

import org.andy.datasource.domain.p.User;
import org.andy.datasource.domain.s.Message;
import org.andy.datasource.repository.p.UserRepository;
import org.andy.datasource.repository.s.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ==========================================================================
 * MultiDataSourceController
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2019/2/22 15:37
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 * ==========================================================================
 */

@RequestMapping("/multi")
@RestController
public class MultiDataSourceController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;


    @Transactional
    @GetMapping("/add")
    public void add() {

        userRepository.save(new User("aaa", 10));
        userRepository.save(new User("bbb", 20));
        userRepository.save(new User("ccc", 30));
        userRepository.save(new User("ddd", 40));
        userRepository.save(new User("eee", 50));

        messageRepository.save(new Message("o1", "aaaaaaaaaa"));
        messageRepository.save(new Message("o2", "bbbbbbbbbb"));
        messageRepository.save(new Message("o3", "cccccccccc"));
        int a = 1 / 0;
    }
}
