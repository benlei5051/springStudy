package org.andy.beans.powermock.systemdemo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * ==========================================================================
 * FlySunDemoTest
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2019/5/16 17:48
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 * ==========================================================================
 */
@RunWith(PowerMockRunner.class)
public class FlySunDemoTest {

    /*Mock系统类的静态和final方法  和Mock普通对象的静态方法、final方法一样*/
    @Test
    @PrepareForTest(FlySunDemo.class)
    public void testCallSystemStaticMethod(){
        FlySunDemo demo = new FlySunDemo();
        PowerMockito.mockStatic(System.class);
        PowerMockito.when(System.getProperty("aaa")).thenReturn("bbb");
        Assert.assertEquals("bbb", demo.callSystemStaticMethod("aaa"));
    }
}