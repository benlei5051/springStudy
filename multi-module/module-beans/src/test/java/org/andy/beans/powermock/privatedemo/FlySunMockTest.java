package org.andy.beans.powermock.privatedemo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * ==========================================================================
 * FlySunMockTest
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2019/5/16 17:46
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 * ==========================================================================
 */
@RunWith(PowerMockRunner.class)
public class FlySunMockTest {

    @Test
    @PrepareForTest(FlySunDemo.class)   /* 注解里写的类是私有方法所在的类。*/
    public void testCallFinalMethod() throws Exception {
        FlySunDemo demo = PowerMockito.mock(FlySunDemo.class);
        PowerMockito.when(demo.callPrivateMethod()).thenCallRealMethod();
        PowerMockito.when(demo, "isAlive").thenReturn(true);
        Assert.assertTrue(demo.callPrivateMethod());
    }
}