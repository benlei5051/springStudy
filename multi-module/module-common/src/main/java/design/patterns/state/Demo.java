/*------------------------------------------------------------------------------
 * Demo
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/28 11:56
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package design.patterns.state;

public class Demo {
    public static void main(String[] args) {

        FileContext fileContext = new FileContext();
        fileContext.getFileState();
        //切换为正在处理状态
        fileContext.Dealing();
        fileContext.getFileState();
        //切换为已经处理状态
        fileContext.HasDeal();
        fileContext.getFileState();
    }
}
