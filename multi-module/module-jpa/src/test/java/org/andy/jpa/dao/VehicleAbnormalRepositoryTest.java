package org.andy.jpa.dao;

import org.andy.jpa.domain.User;
import org.andy.jpa.domain.VehicleAbnormalMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

/**
 * @author: andy
 * @Date: 2017/11/2 14:44
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional   //事物会回滚，在测试中，所有保存的数据都不会进入数据库
public class VehicleAbnormalRepositoryTest {
    @Autowired
    public VehicleAbnormalMessageRepository vehicleAbnormalRepository;
    @Autowired
    public UserRepository userRepository;

    @Test
    public void testFindById(){
        VehicleAbnormalMessage abnormalMessage = vehicleAbnormalRepository.findOne(1L);
        System.out.println(abnormalMessage.getMsg()+"--------------------");
    }
    @Test
    public void testSave(){
        VehicleAbnormalMessage message=new VehicleAbnormalMessage();
        message.setCarNo("ttttttttttt");
        message.setMsg("test5555---");
        vehicleAbnormalRepository.save(message);
    }

}