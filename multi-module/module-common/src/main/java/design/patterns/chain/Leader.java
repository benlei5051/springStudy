/*------------------------------------------------------------------------------
 * Leader
 * @Description: 抽象处理类
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/28 13:45
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package design.patterns.chain;

public abstract class Leader {
    protected String name;
    protected Leader successor;

    public Leader(String name) {
        this.name = name;
    }

    public void setSuccessor(Leader successor) { // 设置下一个处理者
        this.successor = successor;
    }

    public abstract void handleRequest(LeaveRequest request); // 处理请求
}
