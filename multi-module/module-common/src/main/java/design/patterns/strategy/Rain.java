/*------------------------------------------------------------------------------
 * ConcreteStateB
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/28 11:21
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package design.patterns.strategy;

public class Rain implements State {

    @Override
    public String doAction() {
        return "下雨了在家里打麻将";
    }
}
