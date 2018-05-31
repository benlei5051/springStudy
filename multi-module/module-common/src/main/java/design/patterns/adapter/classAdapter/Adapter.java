package design.patterns.adapter.classAdapter;

/**
 * @author: andy
 * @Date: 2018/3/13 10:46
 * @Description:适配器使用多继承或继承再实现
 */
//中转站适配器
public class Adapter extends Source implements Targetable {
    @Override
    public void method2() {
        super.method1();
    }
}
