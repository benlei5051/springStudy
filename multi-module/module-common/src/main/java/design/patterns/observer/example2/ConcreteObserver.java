package design.patterns.observer.example2;

/**
 * @author: andy
 * @Date: 2018/3/21 18:28
 * @Description:
 */
public class ConcreteObserver implements Observer{
    
    private String observerName;
    
    private String remindContent;

    public String getRemindContent() {
        return remindContent;
    }

    public void setRemindContent(String remindContent) {
        this.remindContent = remindContent;
    }

    @Override
    public void setObserverName(String observerName) {
       this.observerName =  observerName;
    }

    @Override
    public String getObserverName() {
        return observerName;
    }

    @Override
    public void update(WeatherSubject weatherSubject) {
        String weatherContent = ((ConcreteWeatherSubject) weatherSubject).getWeatherContent();
        System.out.println(observerName + "收到了消息");
    }
}
