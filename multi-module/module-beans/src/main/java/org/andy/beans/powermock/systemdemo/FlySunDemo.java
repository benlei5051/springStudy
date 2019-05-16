package org.andy.beans.powermock.systemdemo;

/**
 * ==========================================================================
 * FlySunDemo
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2019/5/16 17:48
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 * ==========================================================================
 */
public class FlySunDemo {
    public String callSystemStaticMethod(String str) {
        return System.getProperty(str);
    }
}
