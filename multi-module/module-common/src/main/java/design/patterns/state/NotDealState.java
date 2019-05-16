/*------------------------------------------------------------------------------
 * NotDealState
 * @Description: 未处理状态类
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/28 11:55
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package design.patterns.state;

public class NotDealState  implements State{

    @Override
    public void fileState() {
        // TODO Auto-generated method stub
        System.out.println("文件未处理状态");
    }
}
