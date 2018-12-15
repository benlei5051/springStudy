/*------------------------------------------------------------------------------
 * QltRunnable
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/11/14 17:58
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.thread.threadPool.retry;

public interface  QltRunnable{
    /**
     * 程序正常的执行逻辑
     */
    void run() ;

    /**
     * 当达到最大重试次数后异常的处理逻辑
     */
    void error(Exception e) ;
}
