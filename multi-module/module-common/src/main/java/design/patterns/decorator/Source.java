package design.patterns.decorator;

/**
 * @author: andy
 * @Date: 2018/3/13 11:08
 * @Description:
 */
public class Source implements Sourceable {
    @Override
    public void method() {
        System.out.println("the original method!");
    }
}
