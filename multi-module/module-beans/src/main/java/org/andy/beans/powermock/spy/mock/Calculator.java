package org.andy.beans.powermock.spy.mock;

/**
 * ==========================================================================
 * Calculator
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2019/5/16 15:25
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 * ==========================================================================
 */
public class Calculator {
    private int sumXX(int a, int b) {
        return a + b;
    }

    public int callSumXX(int a, int b) {
        return sumXX(a, b);
    }
}
