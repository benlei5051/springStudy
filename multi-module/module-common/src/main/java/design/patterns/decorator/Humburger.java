package design.patterns.decorator;

/**
 * @author: andy
 * @Date: 2018/3/19 14:24
 * @Description:被装饰者抽象类
 */
public abstract class Humburger {
    protected String name;

    public String getName() {
        return name;
    }

    public abstract double getPrice();
}
