/*------------------------------------------------------------------------------
 * Demo
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/28 13:50
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package design.patterns.chain;

public class Demo {
    public static void main(String args[]) {
        Leader objDirector, objManager, objGeneralManager, objViceGeneralManager;

        objDirector = new Director("王明");
        objManager = new Manager("赵强");
        objGeneralManager = new GeneralManager("李波");
        objViceGeneralManager = new ViceGeneralManager("肖红");

        // 建立职责链
        objDirector.setSuccessor(objManager); // 主任上级是经理
        objManager.setSuccessor(objViceGeneralManager); // 经理上级是副经理
        objViceGeneralManager.setSuccessor(objGeneralManager); // 副经理上级是总经理

        LeaveRequest lr1 = new LeaveRequest("张三", 2);
        objDirector.handleRequest(lr1);

        LeaveRequest lr2 = new LeaveRequest("李四", 5);
        objDirector.handleRequest(lr2);

        LeaveRequest lr3 = new LeaveRequest("王五", 15);
        objDirector.handleRequest(lr3);

        LeaveRequest lr4 = new LeaveRequest("赵六", 25);
        objDirector.handleRequest(lr4);
    }
}
