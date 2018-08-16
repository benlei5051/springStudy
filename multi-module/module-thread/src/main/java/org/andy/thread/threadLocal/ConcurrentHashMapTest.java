package org.andy.thread.threadLocal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/8/15 14:04
 * @version: V1.0
 */
public class ConcurrentHashMapTest {
    private static ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();
//    private static Map<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) {
        new Thread("Thread1"){
            @Override
            public void run() {
                map.put(3, 33);
            }
        }.start();

        new Thread("Thread2"){
            @Override
            public void run() {
                map.put(4, 44);
            }
        }.start();

        new Thread("Thread3"){
            @Override
            public void run() {
                map.put(7, 77);
            }
        }.start();
        System.out.println(map);
    }
}
