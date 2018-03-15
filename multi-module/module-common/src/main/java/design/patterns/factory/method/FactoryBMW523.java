package design.patterns.factory.method;

/**
 * @author: andy
 * @Date: 2018/3/12 14:44
 * @Description:
 */
public class FactoryBMW523 implements FactoryBMW{
    @Override
    public BMW523 createBMW() {
        return new BMW523();
    }
}
