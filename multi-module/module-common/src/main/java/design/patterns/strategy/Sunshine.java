/*------------------------------------------------------------------------------
 * ConcreteStateA
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/28 11:20
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package design.patterns.strategy;

public class Sunshine implements State {

    @Override
    public String doAction() {
        return "晴天出去玩";
    }
}
