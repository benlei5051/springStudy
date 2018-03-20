package design.patterns.observer.util;

/**
 * @author: andy
 * @Date: 2018/3/20 17:15
 * @Description:
 */
public class Client {
    public static void main(String[] args){
        //创建一个天气作为目标
        ConcreteWeatherSubject concreteWeatherSubject = new ConcreteWeatherSubject();
        //创建一个黄明的女朋友，作为观察者
        ConcreteObserver girl = new ConcreteObserver();
        girl.setObserverName("黄明的女朋友");
        ConcreteObserver mum = new ConcreteObserver();
        mum.setObserverName("黄明的老妈");
        //注册观察者
        concreteWeatherSubject.addObserver(girl);
        concreteWeatherSubject.addObserver(mum);
        //目标更新天气情况
        concreteWeatherSubject.setContent("天气晴，气温28度");
    }
}
