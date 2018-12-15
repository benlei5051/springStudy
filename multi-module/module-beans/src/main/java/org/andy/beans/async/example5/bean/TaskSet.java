/*------------------------------------------------------------------------------
 * TaskSet
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/5 17:30
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.beans.async.example5.bean;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashSet;
import java.util.Set;

@Data
@Component
public class TaskSet {
    //只包含了一个HashSet的成员变量。这里可以考虑用ConcurrentHashMap来实现高效并发，
    private Set<DeferredResult<ResponseMsg<String>>> set = new HashSet<>();
}
