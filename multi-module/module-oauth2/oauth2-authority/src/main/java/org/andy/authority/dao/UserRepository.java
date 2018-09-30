package org.andy.authority.dao;

import org.andy.authority.domain.User;
import org.andy.authority.support.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author sean
 * 2017/11/2.
 */
public interface UserRepository extends BaseRepository<User, String> {

    @Query("select new map(u.id as value,u.realName as label) from User u where u.orgCode like ?1%")
    List<Map<String, String>> findByOrgCodeforAccount(String code);

    /**
     * 通过组织层级编码查找组织下的所用用户数
     *
     * @param levelCode 组织的层级编码
     * @return 用户数
     */
    @Query("select count (u.id) from User u where u.orgCode like ?1%")
    int findByOrgCode(String levelCode);

    /**
     * 通过组织层级编码查找组织下的所用用户
     *
     * @param levelCodes 组织的层级编码
     * @return list
     */
    @Query("select u from User u where u.orgCode in ?1")
    List<User> searchDealer(List<String> levelCodes);


    /**
     * 通过账户名查找用户
     *
     * @param username 帐户名
     * @return 用户
     */
    @Query("select u from User u where u.account.username = ?1")
    User findByAccount_Username(String username);

    /**
     * 批量删除
     *
     * @param ids     用户ids
     * @param orgCode 组织编码
     */
    @Modifying
    @Query("update User u set u.delFlag=1 where u.id in ?1 and u.orgCode like ?2%")
    void batchesDelete(List<String> ids, String orgCode);

    /**
     * 通过ids查询集合
     *
     * @param ids id集合
     * @return users
     */
    List<User> findByIdIn(List<String> ids);


    @Modifying
    @Transactional
    @Query("update User a set a.account.password=?2, a.account.expireTime=?3, a.account.active=true where a.id=?1")
    int updatePassword(String accounId, String newPassword, Date pwdExpDay);

    @Modifying
    @Query("update User a set a.account.expireTime=?2 where a.orgCode = ?1")
    int resetExpireTime(String orgCode, Date date);

    @Query(value = "SELECT MAX(substring_index(u.username,'-', -1)) FROM opt_user u  WHERE u.username LIKE  '%-%'", nativeQuery = true)
    Integer findUsernameMaxSum();

}
