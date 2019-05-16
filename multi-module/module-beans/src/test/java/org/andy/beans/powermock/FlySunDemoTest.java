package org.andy.beans.powermock;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import java.io.File;

/**
 * ==========================================================================
 * FlySunDemoTest
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2019/5/16 17:32
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 * ==========================================================================
 */
public class FlySunDemoTest {

    @Test
    public void testCallArgumentInstance() {
        //mock出入参File对象
        File file = PowerMockito.mock(File.class);
        FlySunDemo demo = new FlySunDemo();
        PowerMockito.when(file.exists()).thenReturn(true);
        Assert.assertTrue(demo.callArgumentInstance(file));
    }
}