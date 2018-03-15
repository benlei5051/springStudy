package design.patterns.factory.simplefactory;

/**
 * @author: andy
 * @Date: 2018/3/12 14:39
 * @Description:简单工厂模式
 */
public class Test {
    public static void main(String[] args){
        Factory factory =new Factory();
        BMW bmw320 = factory.createBMW(320);
        BMW bmw523 = factory.createBMW(523);
    }
}
