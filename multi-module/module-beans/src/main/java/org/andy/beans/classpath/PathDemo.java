/*------------------------------------------------------------------------------
 * Test
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/17 10:58
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.beans.classpath;

public class PathDemo {
    public static void main(String[] args) {
        System.out.println(PathDemo.class.getResource("/").getPath());
        System.out.println(PathDemo.class.getResource("/test.properties").getPath());
        System.out.println(PathDemo.class.getResource("").getPath());
    }

}
