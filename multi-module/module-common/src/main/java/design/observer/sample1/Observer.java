package design.observer.sample1;

/**
 * @author: andy
 * @Date: 2018/3/20 16:25
 * @Description:
 */
public interface Observer {
    //观察者拿到具体的目标对象，观察者按需要自己去拉取
    void update(Subject subject);
}
