package org.andy.datasource.repository.s;

import org.andy.datasource.domain.s.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ==========================================================================
 * MessageRepository
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2019/2/13 16:15
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 * ==========================================================================
 */
public interface MessageRepository extends JpaRepository<Message, Long> {

}
