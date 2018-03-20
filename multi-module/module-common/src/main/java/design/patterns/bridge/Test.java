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


        DriverManager driverManager2 = new MyDriverManager2();
        Driver driver2 = new DB2Driver();
        driverManager2.setDriver(driver2);
        driverManager2.connect();




    }
}
