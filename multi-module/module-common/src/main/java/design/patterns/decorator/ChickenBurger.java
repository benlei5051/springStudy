package design.patterns.decorator;

/**
 * @author: andy
 * @Date: 2018/3/19 14:26
 * @Description: 被装饰者实现类:鸡腿堡类（被装饰者的初始状态，有些自己的简单装饰）
 */

public class ChickenBurger extends Humburger {
    public ChickenBurger() {
        name =  "鸡腿堡";
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public double getPrice() {
        return 10;
    }
}
