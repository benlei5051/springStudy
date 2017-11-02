package org.andy.jpa.dao;

import org.andy.jpa.domain.User;
import org.andy.jpa.domain.VehicleAbnormalMessage;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: andy
 * @Date: 2017/11/2 14:39
 * @Description:
 */
public interface VehicleAbnormalMessageRepository extends JpaRepository<VehicleAbnormalMessage, Long>,
        JpaSpecificationExecutor<VehicleAbnormalMessage> {

}
