/*------------------------------------------------------------------------------
 * Context
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/28 11:19
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package design.patterns.strategy;

//定义当前的状态
public class Context {

    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
    public String stateMessage(){
        return state.doAction();
    }
}