/*------------------------------------------------------------------------------
 * Demo
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/28 11:22
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package design.patterns.strategy;

public class Demo {

    public static void main(String[] args) {
        Context context = new Context();
        context.setState(new Rain());
        System.out.println(context.stateMessage());
        context.setState(new Sunshine());
        System.out.println(context.stateMessage());
    }

}
