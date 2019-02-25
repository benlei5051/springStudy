package org.andy.datasource.repository.p;

import org.andy.datasource.domain.p.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ==========================================================================
 * UserRepository
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2019/2/13 16:13
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 * ==========================================================================
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
