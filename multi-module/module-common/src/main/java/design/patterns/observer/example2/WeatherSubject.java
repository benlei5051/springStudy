package design.patterns.observer.example2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: andy
 * @Date: 2018/3/21 17:58
 * @Description:
 */
public abstract class WeatherSubject {

    protected List<Observer> list = new ArrayList<>();

    public void attach(Observer watcher){
        list.add(watcher);
    }

    public void detach(Observer watcher){
        list.remove(watcher);
    }

    protected abstract void notifyWatchers();


}
