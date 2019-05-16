/*------------------------------------------------------------------------------
 * FileContext
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/28 11:56
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package design.patterns.state;

public class FileContext {

    //默认情况下未处理状态
    private State fileState = new NotDealState();

    /**
     * 文件状态切换为未处理状态
     */
    public void notDeal(){

        this.fileState = new NotDealState();
    }

    /**
     * 文件状态切换为正在处理状态
     */
    public void Dealing(){

        this.fileState = new DealingState();
    }

    /**
     * 文件状态切换为已经处理状态
     */
    public void HasDeal(){

        this.fileState = new HasDealState();
    }

    /**
     * 获取当前文件的状态
     */
    public void getFileState(){
        fileState.fileState();
    }
}
