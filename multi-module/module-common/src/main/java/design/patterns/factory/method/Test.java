package design.patterns.factory.method;

import design.patterns.factory.simplefactory.Factory;

/**
 * @author: andy
 * @Date: 2018/3/12 14:45
 * @Description:工厂方法模式
 */
public class Test {
    public static void main(String[] args){
        FactoryBWM320 factoryBMW320 = new FactoryBWM320();
        BMW320 bmw320 = factoryBMW320.createBMW();

        FactoryBMW523 factoryBMW523 = new FactoryBMW523();
        BMW523 bmw523 = factoryBMW523.createBMW();
    }
}
