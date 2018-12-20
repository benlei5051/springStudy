/*------------------------------------------------------------------------------
 * Test
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/17 10:58
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args){
        String date2 = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date(1535522337*1000L));
        System.out.println(date2);
    }
}
