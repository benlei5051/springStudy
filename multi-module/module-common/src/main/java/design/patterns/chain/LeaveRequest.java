/*------------------------------------------------------------------------------
 * LeaveRequest
 * @Description:  休假请求
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/28 13:44
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package design.patterns.chain;

public class LeaveRequest {
    private String leaveName; // 休假人
    private int leaveDays; // 休假天数

    public LeaveRequest(String leaveName, int leaveDays) {
        this.leaveName = leaveName;
        this.leaveDays = leaveDays;
    }

    public void setLeaveName(String leaveName) {
        this.leaveName = leaveName;
    }

    public void setLeaveDays(int leaveDays) {
        this.leaveDays = leaveDays;
    }

    public String getLeaveName() {
        return (this.leaveName);
    }

    public int getLeaveDays() {
        return (this.leaveDays);
    }
}
