/*------------------------------------------------------------------------------
 * ResponseMsg
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/11/21 18:12
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.beans.async.example5.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMsg<T> {

    private int code;

    private String msg;

    private T data;

}
