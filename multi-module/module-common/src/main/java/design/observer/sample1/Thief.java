package design.observer.sample1;

/**
 * @author: andy
 * @Date: 2018/3/20 16:26
 * @Description:具体的观察者:强盗
 */
public class Thief implements Observer {
    @Override
    public void update(Subject subject) {
        String vehicleAction = ((Transporter) subject).getVehicleAction();
        System.out.println(vehicleAction+", 强盗准备动手");
    }
}
