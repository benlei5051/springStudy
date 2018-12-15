/*------------------------------------------------------------------------------
 * Test
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/11/14 18:01
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.thread.threadPool.retry;

public class Test {
    public static void main(String[] args) {
            //线程数设置为5
            QltThreadPoolExecutor executor = new QltThreadPoolExecutor();
            //开启一个线程
            executor.execute(new QltRunnable() {
                @Override
                public void run() {
                    int i = 6 / 0;
                }

                @Override
                public void error(Exception e) {
                    System.out.println("出错了");
                }
            });

    }
}
