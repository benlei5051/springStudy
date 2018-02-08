package org.andy.jpa.dao;

import org.andy.jpa.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author: andy
 * @Date: 2018/1/30 18:43
 * @Description:
 */
public interface InfoRepository extends JpaRepository<Info, Long> {

    @Query(nativeQuery = true,name = "messageMapping",value = "SELECT id, msg from info" )
    List<Info> findInfos();
}
