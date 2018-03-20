package design.patterns.proxy;

/**
 * @author: andy
 * @Date: 2018/3/13 11:18
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        Sourceable source = new Proxy();
        source.method();
    }
}
