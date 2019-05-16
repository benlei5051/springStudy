package org.andy.beans.powermock.newdemo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

/**
 * ==========================================================================
 * FlySunMockTest
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2019/5/16 17:33
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 * ==========================================================================
 */
@RunWith(PowerMockRunner.class)
public class FlySunMockTest {

    /*当使用PowerMockito.whenNew方法时，必须加注解@PrepareForTest和@RunWith。
    注解@PrepareForTest里写的类是需要mock的new对象代码所在的类。*/

    @Test
    @PrepareForTest(FlySunDemo.class)
    public void testCallArgumentInstance(){
        File file = PowerMockito.mock(File.class);
        try {
            PowerMockito.whenNew(File.class).withArguments("bbb").thenReturn(file);
            FlySunDemo demo = new FlySunDemo();
            PowerMockito.when(file.exists()).thenReturn(true);
            Assert.assertTrue(demo.callArgumentInstance("bbb"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
