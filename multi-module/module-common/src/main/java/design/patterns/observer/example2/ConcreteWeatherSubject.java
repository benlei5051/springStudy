package design.patterns.observer.example2;

/**
 * @author: andy
 * @Date: 2018/3/21 18:13
 * @Description:
 */
public class ConcreteWeatherSubject extends WeatherSubject {

    private String weatherContent;

    public String getWeatherContent() {
        return weatherContent;
    }

    public void setRemindContent(String weatherContent) {
        this.weatherContent = weatherContent;
        this.notifyWatchers();
    }

    @Override
    protected void notifyWatchers() {
        for (Observer observer : list) {
            if ("下雨".equals(this.getWeatherContent())) {
                if ("黄明的女朋友".equals(observer.getObserverName())) {
                    observer.update(this);
                }
                if ("黄明的老妈".equals(observer.getObserverName())) {
                    observer.update(this);
                }
            }
            if ("下雪".equals(this.getWeatherContent())) {
                if ("黄明的老妈".equals(observer.getObserverName())) {
                    observer.update(this);
                }
            }

        }
    }


}
