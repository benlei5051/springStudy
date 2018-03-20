package design.patterns.decorator;

/**
 * @author: andy
 * @Date: 2018/3/19 14:31
 * @Description:装饰者:辣椒
 */
public class Chilli extends Condiment {
    private Humburger humburger;

    public Chilli(Humburger humburger) {
        this.humburger = humburger;
    }

    @Override
    public String getName() {
        return humburger.getName() + "加辣椒";
    }

    @Override
    public double getPrice() {
        return humburger.getPrice(); //辣椒是免费的哦
    }
}
