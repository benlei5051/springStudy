package design.patterns.adapter.classAdapter;

/**
 * @author: andy
 * @Date: 2018/3/13 10:46
 * @Description:
 */
public class Adapter extends Source implements Targetable {
    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }
}
