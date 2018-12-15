/*------------------------------------------------------------------------------
 * StatusRepository
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/14 10:14
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.database.lock.example4.dao;

import org.andy.database.lock.example4.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StatusRepository extends JpaRepository<Status, Long> {

    @Modifying
    @Query("update Status s set s.version = :value where s.version = :version")
    int update(@Param("version") final long version, @Param("value") final long value);

}
