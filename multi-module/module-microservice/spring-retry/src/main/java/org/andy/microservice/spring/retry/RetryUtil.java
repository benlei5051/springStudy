/*------------------------------------------------------------------------------
 * RetryUtil
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/11/20 16:40
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.microservice.spring.retry;

import java.lang.reflect.Method;

public class RetryUtil {

    public static void main(String[] args){
        RetryUtil.subMethod("aaaa","bbbb");

    }
    public static void subMethod(String param1, String param2) {
        System.out.println(param1 + param2);
        RetryUtil.setRetryTimes(3).retry(param1, param2);
    }

    private static ThreadLocal<Integer> retryTimesInThread = new ThreadLocal<>();

    /**
     * 设置当前方法重试次数
     *
     * @param retryTimes
     * @return
     */
    public static RetryUtil setRetryTimes(Integer retryTimes) {
        if (retryTimesInThread.get() == null) {
            retryTimesInThread.set(retryTimes);
        }
        return new RetryUtil();
    }

    /**
     * 重试当前方法
     * <p>按顺序传入调用者方法的所有参数</p>
     *
     * @param args
     * @return
     */
    public Object retry(Object... args) {
        try {
            Integer retryTimes = retryTimesInThread.get();
            if (retryTimes <= 0) {
                retryTimesInThread.remove();
                return null;
            }
            retryTimesInThread.set(--retryTimes);
            //获取当前方法（retry）的上层方法名和上层类名。
            // Thread.currentThread().getStackTrace() 得到线程的方法栈数组，
            // 数组的第二个元素 Thread.currentThread().getStackTrace() [1]  为当前方法栈，
            // 第三个元素 Thread.currentThread().getStackTrace() [2] 为上层方法栈，
            // 通过上层方法的栈帧得到上层方法的方法名和类名。
            String upperClassName = Thread.currentThread().getStackTrace()[2].getClassName();
            String upperMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();

            Class clazz = Class.forName(upperClassName);
            Object targetObject = clazz.newInstance();
            Method targetMethod = null;
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.getName().equals(upperMethodName)) {
                    targetMethod = method;
                    break;
                }
            }
            if (targetMethod == null) {
                return null;
            }
            targetMethod.setAccessible(true);
            return targetMethod.invoke(targetObject, args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
