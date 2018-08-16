package design.observer.sample1;

/**
 * @author: andy
 * @Date: 2018/3/20 16:38
 * @Description:
 */
public class Test {
    public static void main(String[] args){
        //创建目标
        Transporter transporter = new Transporter();
        //创建观察者
        Observer security = new Security();
        Observer police = new Police();
        Observer thief = new Thief();
        //注册观察者
        transporter.addWatcher(security);
        transporter.addWatcher(police);
        transporter.addWatcher(thief);

        //目标发布行为通知
        transporter.setVehicleAction("运钞车开始移动");
    }
}
