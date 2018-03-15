package design.patterns.bridge;

/**
 * @author: andy
 * @Date: 2018/3/13 16:52
 * @Description:
 */
public class Test {
    public static void main(String[] args){
        DriverManager driverManager = new MyDriverManager();
        Driver driver1 = new MysqlDriver();
        driverManager.setDriver(driver1);
        driverManager.connect();


    }
}
