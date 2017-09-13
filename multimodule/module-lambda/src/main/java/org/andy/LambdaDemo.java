package org.andy;

import java.util.Optional;

/**
 * @author: andy
 * @Date: 2017/9/13 17:20
 * @Description:
 */
public class LambdaDemo {
    public static void main(String[] args){
        LambdaDemo.test1();
    }

    /**
     * 处理空指针异常
     */
    public static void test1(){
    /*    if(null == str) {
            return 0;
        }
        return str.length();*/
        String a=null;
        System.out.println(Optional.ofNullable(a).map(String::length).orElse(0));
    }
}
