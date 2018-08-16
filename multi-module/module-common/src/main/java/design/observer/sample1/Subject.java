package design.observer.sample1;

/**
 * @author: andy
 * @Date: 2018/3/20 16:31
 * @Description:抽象的被观察者
 */
public interface Subject {

    public void addWatcher(Observer watcher);

    public void removeWatcher(Observer watcher);

    public void notifyWatchers(Subject subject);
}
