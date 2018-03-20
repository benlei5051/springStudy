package design.patterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: andy
 * @Date: 2018/3/20 16:17
 * @Description:具体的被观察者:运输车
 */
public class Transporter implements Subject{

    private String vehicleAction;

    private List<Observer> list = new ArrayList<>();

    public String getVehicleAction() {
        return vehicleAction;
    }

    public void setVehicleAction(String vehicleAction) {
        this.vehicleAction = vehicleAction;
        this.notifyWatchers(this);
    }

    @Override
    public void addWatcher(Observer watcher) {
        list.add(watcher);
    }

    @Override
    public void removeWatcher(Observer watcher) {
        list.remove(watcher);
    }

    @Override
    public void notifyWatchers(Subject subject) {
        for (Observer observer : list) {
            observer.update(subject);
        }
    }
}
