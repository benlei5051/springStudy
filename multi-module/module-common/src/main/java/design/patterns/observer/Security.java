package design.patterns.observer;

/**
 * @author: andy
 * @Date: 2018/3/20 16:26
 * @Description:具体的观察者:保镖
 */
public class Security implements Observer {
    @Override
    public void update(Subject subject) {
        String vehicleAction = ((Transporter) subject).getVehicleAction();
        System.out.println(vehicleAction+", 保安贴身保护");
    }
}
