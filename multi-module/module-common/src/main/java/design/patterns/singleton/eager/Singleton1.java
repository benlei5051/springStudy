package design.patterns.singleton.eager;

/**
 * @author: andy
 * @Date: 2018/3/12 15:24
 * @Description: 饿汉式：在类初始化时，已经自行实例化
 */
public class Singleton1 {

    private Singleton1() {}

    private static Singleton1 single=new Singleton1();

    public static Singleton1 getInstance() {
        return single;
    }
}
