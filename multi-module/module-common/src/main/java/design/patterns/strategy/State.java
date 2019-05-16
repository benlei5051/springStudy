/*------------------------------------------------------------------------------
 * State
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/28 11:18
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package design.patterns.strategy;

public interface State {
    //根据不同的状态做不懂的行为
    String doAction();
}
