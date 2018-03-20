package design.patterns.observer.util;

import java.util.Observable;
import java.util.Observer;

/**
 * @author: andy
 * @Date: 2018/3/20 17:10
 * @Description:
 */
public class ConcreteObserver implements Observer {
    //    观察者名称的变量
    private String observerName;

    public String getObserverName() {
        return observerName;
    }

    public void setObserverName(String observerName) {
        this.observerName = observerName;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(observerName + "收到了消息，目标对象推送过来的是"+arg);
        System.out.println(observerName + "收到了消息，主动到目标对象中去拉，拉的内容是"+((ConcreteWeatherSubject)o).getContent());
    }
}
