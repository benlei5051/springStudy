/*------------------------------------------------------------------------------
 * Director
 * @Description: 主任审批
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/28 13:46
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package design.patterns.chain;

public class Director extends Leader {
    public Director(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveRequest request) {
        // 小于三天主任审批
        if (request.getLeaveDays() < 3) {
            System.out.println("主任" + name + "审批员工" + request.getLeaveName() + "的请假条，请假天数为" + request.getLeaveDays() + "天。");
        } else {
            if (this.successor != null) {
                this.successor.handleRequest(request);
            }
        }
    }
}
