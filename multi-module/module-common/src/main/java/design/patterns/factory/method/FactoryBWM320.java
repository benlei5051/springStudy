package design.patterns.factory.method;

/**
 * @author: andy
 * @Date: 2018/3/12 14:43
 * @Description:
 */
public class FactoryBWM320 implements FactoryBMW{
    @Override
    public BMW320 createBMW() {
        return new BMW320();
    }
}
