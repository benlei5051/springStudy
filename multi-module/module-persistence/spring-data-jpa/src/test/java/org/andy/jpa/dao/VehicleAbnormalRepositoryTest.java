package org.andy.jpa.dao;

import org.andy.jpa.entity.VehicleAbnormalMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: andy
 * @Date: 2017/11/2 14:44
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional   //事物会回滚，在测试中，所有保存的数据都不会进入数据库
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