package design.patterns.observer.example2;

/**
 * @author: andy
 * @Date: 2018/3/21 18:12
 * @Description:
 */
public interface Observer {

    //设置观察者名称
    void setObserverName(String observerName);
    //获得观察者名称
    String getObserverName();
    //跟新的接口
    void update(WeatherSubject weatherSubject);

}
