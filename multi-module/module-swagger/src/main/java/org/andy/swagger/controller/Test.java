package org.andy.swagger.controller;

import java.util.Calendar;
import java.util.Date;

/**
 * @author: andy
 * @Date: 2018/1/5 14:49
 * @Description:
 */
public class Test {
    public static void main(String[] args){
        Date date =new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 下一天的 凌晨00:00:00过期
     //   cal.add(Calendar.DATE, 1);
      //  cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        System.out.println(cal.getTime());
    }
}
