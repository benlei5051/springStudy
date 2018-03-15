package design.patterns.singleton.lazy;

/**
 * @author: andy
 * @Date: 2018/3/12 15:24
 * @Description: 懒汉式： 在getInstance方法上加同步
 */
public class Singleton1 {

    private Singleton1() {}

    private static Singleton1 single=null;

    public static synchronized Singleton1 getInstance() {
        if (single == null) {
            single = new Singleton1();
        }
        return single;
    }
}
