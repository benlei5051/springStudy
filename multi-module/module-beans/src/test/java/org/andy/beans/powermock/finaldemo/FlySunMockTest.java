package org.andy.beans.powermock.finaldemo;

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
 * @date: 2019/5/16 17:39
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 * ==========================================================================
 */
@RunWith(PowerMockRunner.class)
public class FlySunMockTest {

    /*说明： 当需要mock final方法的时候，必须加注解@PrepareForTest和@RunWith。
    注解@PrepareForTest里写的类是final方法所在的类。*/

    @Test
    @PrepareForTest(ClassDependency.class)
    public void testCallFinalMethod() {
        ClassDependency refer = PowerMockito.mock(ClassDependency.class);
        PowerMockito.when(refer.isAlive()).thenReturn(true);
        FlySunDemo demo = new FlySunDemo();
        Assert.assertTrue(demo.callFinalMethod(refer));
    }
}
