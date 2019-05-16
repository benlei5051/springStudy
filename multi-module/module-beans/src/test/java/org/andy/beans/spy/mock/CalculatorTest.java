package org.andy.beans.spy.mock;

import org.andy.beans.powermock.spy.mock.Calculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;

/**
 * ==========================================================================
 * CalculatorTest
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2019/5/16 15:26
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 * ==========================================================================
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Calculator.class})
public class CalculatorTest {
    @Test
    public void testSumXX() throws Exception {
//        Calculator cal= PowerMockito.spy(new Calculator());
//        PowerMockito.when(cal,"sumXX",anyInt(),anyInt()).thenReturn(2);
//        assertEquals(2, cal.callSumXX(1, 2));

        /*@mock写法: cal中所有的方法都不是真实的且默认返回null，
        用mock去模拟返回值时sumXX方法会先执行一次，而因为执行的不是真实的方法所以并没有什么影响。*/
        Calculator cal=PowerMockito.mock(Calculator.class);
        PowerMockito.when(cal,"sumXX",anyInt(),anyInt()).thenReturn(2);
        System.out.println(cal.callSumXX(1, 2));


//        @spy写法: cal中所有的方法都是真实的，用when..thenReturn时就会去执行真实的私有方法，
// 那么私有方法里面所有的代码都会执行一遍，这样是不可行的，因为很有可能私有方法就会依赖真实的环境。
// 需要改用doReturn..when才会不执行真实的方法。

//        Calculator cal=PowerMockito.spy(new Calculator());
//
//        PowerMockito.doReturn(2).when(cal,"sumXX",anyInt(),anyInt());
//
//        assertEquals(2, cal.callSumXX(1, 2));

    }

//    使用@mock模拟私有方法测试代码如下:
    /*注：因为@mock出来的对象可能已经发生了变化，调用的方法都不是真实的，
    @mock出来的Calculator对应已经不是原来的Calculator，
    在进行sonar覆盖率统计时统计出来的Calculator类覆盖率为0.00%.*/
    @Test
    public void testSumXX1() throws Exception {
        Calculator cal = PowerMockito.mock(Calculator.class);
        PowerMockito.when(cal,"sumXX",anyInt(),anyInt()).thenReturn(2);
        //指明callSumXX调用真实的方法
        PowerMockito.when(cal.callSumXX(anyInt(),anyInt())).thenCallRealMethod();
        assertEquals(2, cal.callSumXX(1, 2));
    }


//    使用@spy模拟私有方法测试代码如下:
    /*注：因为@spy使用的真实的Calculator对象实例，调用的都是真实的方法，所以通过这种方式进行测试，
    在进行sonar覆盖率统计时统计出来的Calculator类覆盖率为50%.*/
    @Test
    public void testSumXX2() throws Exception {
        Calculator cal = PowerMockito.spy(new Calculator());
        PowerMockito.doReturn(2).when(cal,"sumXX",anyInt(),anyInt());
        assertEquals(2, cal.callSumXX(1, 2));
    }
}