package design.patterns.singleton.lazy;

/**
 * @author: andy
 * @Date: 2018/3/12 15:24
 * @Description: 懒汉式： 静态内部类,既实现了线程安全，又避免了同步带来的性能影响。
 */
public class Singleton3 {
    private static class LazyHolder {
        private static final Singleton3 single = new Singleton3();
    }

    private Singleton3() {

    }

    public static final Singleton3 getInstance() {
        return LazyHolder.single;
    }
}
