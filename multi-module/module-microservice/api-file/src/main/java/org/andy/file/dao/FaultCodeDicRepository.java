package org.andy.file.dao;

import org.andy.file.repository.FaultCodeDic;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

/**
 * @author: andy
 * @Date: 2018/4/20 18:05
 * @Description:
 */
public interface FaultCodeDicRepository extends BaseRepository<FaultCodeDic, String> {

    @Modifying
    @Query("update FaultCodeDic o set o.delFlag=?1 where o.sid in ?2")
    int batchDelete(Integer delFag, Collection<String> sids);

    FaultCodeDic findByFaultCode(String faultCode);
}
