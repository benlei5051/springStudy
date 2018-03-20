package design.patterns.bridge;

/**
 * @author: andy
 * @Date: 2018/3/19 10:45
 * @Description:
 */
public class MyDriverManager2 extends DriverManager {
    @Override
    public void connect() {
        System.out.println("before connect");
        super.connect();
        System.out.println("after connect");
    }
}
