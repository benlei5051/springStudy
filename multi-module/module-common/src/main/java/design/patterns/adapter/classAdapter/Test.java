package design.patterns.adapter.classAdapter;

/**
 * @author: andy
 * @Date: 2018/3/13 10:47
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        Targetable target = new Adapter();
        target.method2();
    }
}
