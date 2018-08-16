package design.observer.sample2;

import java.util.Observable;

/**
 * @author: andy
 * @Date: 2018/3/20 17:05
 * @Description: 天气目标的具体实现类
 */
public class ConcreteWeatherSubject extends Observable{
    //天气情况的内容
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        //天气情况有了，就要通知所有的观察者
        //通知之前，在用java中的observer模式的时候，下面这句话不可少
        this.setChanged();
        //拉模型
        //this.notifyObservers();
        this.notifyObservers(content);

    }
}
