package design.patterns.adapter.classAdapter;

/**
 * @author: andy
 * @Date: 2018/3/13 10:45
 * @Description:
 */
public interface Targetable {
    /* 与原类中的方法相同 */
    public void method1();

    /* 新类的方法 */
    public void method2();
}
