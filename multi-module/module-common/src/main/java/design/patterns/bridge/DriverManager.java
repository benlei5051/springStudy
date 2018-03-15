package design.patterns.bridge;

/**
 * @author: andy
 * @Date: 2018/3/13 16:03
 * @Description:
 */
public abstract class DriverManager {
    private Driver driver;

    public void connect(){
        driver.connect();
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
