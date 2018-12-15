/*------------------------------------------------------------------------------
 * Task
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/11/21 18:16
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.beans.async.example2.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.async.DeferredResult;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    private int taskId;

    private DeferredResult<ResponseMsg<String>> taskResult;

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskResult" + "{responseMsg=" + taskResult.getResult() + "}" +
                '}';
    }
}

