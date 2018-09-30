package org.andy.authority.dao;

import org.andy.authority.domain.Role;
import org.andy.authority.domain.RoleUser;
import org.andy.authority.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;


/**
 * @author seangogo
 */
public interface RoleUserRepository extends JpaRepository<RoleUser, String>, JpaSpecificationExecutor<RoleUser> {

    /**
     * 删除用户时级联删除关联角色
     *
     * @param user 删除的用户
     * @return 删除条数
     */
    void deleteByUser(User user);

    @Modifying
    @Transactional
    @Query(value = "DELETE  from RoleUser a where a.role.id in ?1 and a.user.id=?2")
    int deleteforRoleIdsAndaccountId(Set<String> ids, String id);


    @Query("select ra.role.id from RoleUser ra where ra.user.id=?1")
    Set<String> findRoleByUserId(String id);

    @Query("select ra.role from RoleUser ra where ra.user.id=?1")
    List<Role> findRolesByUserId(String id);

    @Modifying
    @Query(value = "DELETE  from RoleUser ru where ru.role.id in ?1 and ru.user.id=?2")
    int deleteforRoleIdsAndUserId(Set<String> ids, String id);

    void deleteByUserIn(List<User> users);
}
