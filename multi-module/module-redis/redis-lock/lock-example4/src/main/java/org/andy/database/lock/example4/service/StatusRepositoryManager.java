/*------------------------------------------------------------------------------
 * StatusRepositoryManager
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/14 10:15
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.database.lock.example4.service;

import org.andy.database.lock.example4.dao.StatusRepository;
import org.andy.database.lock.example4.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StatusRepositoryManager {
    @Autowired
    private StatusRepository repository;

    @Transactional(rollbackFor = Exception.class)
    public boolean updateByVersion() {
        // 查询当前表中的version值
        Status s = repository.findOne(1L);
        // 更新version的值，将version的值设为version+1
        int result = repository.update(s.getVersion(), s.getVersion() + 1);
        // 返回更新结果
        return 1 == result;
    }
}

