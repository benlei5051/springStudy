package design.patterns.singleton.lazy;

/**
 * @author: andy
 * @Date: 2018/3/12 15:24
 * @Description: 懒汉式： 双重检查锁定
 */
public class Singleton2 {

    private Singleton2() {}

    private static Singleton2 single=null;

    public static  Singleton2 getInstance() {
        if (single == null) {
            synchronized (Singleton2.class) {
                if (single == null) {
                    single = new Singleton2();
                }
            }
        }
        return single;
    }
}
