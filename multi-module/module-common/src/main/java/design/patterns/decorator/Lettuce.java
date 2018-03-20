package design.patterns.decorator;

/**
 * @author: andy
 * @Date: 2018/3/19 14:29
 * @Description: 装饰者:生菜
 */
public class Lettuce extends Condiment{

    private Humburger humburger;

    public Lettuce(Humburger humburger) {
        this.humburger = humburger;
    }

    @Override
    public String getName() {
        return humburger.getName()+"加生菜";
    }

    @Override
    public double getPrice() {
        return humburger.getPrice() + 1.5;
    }
}
