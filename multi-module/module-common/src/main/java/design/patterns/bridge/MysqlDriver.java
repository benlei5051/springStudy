package design.patterns.bridge;

/**
 * @author: andy
 * @Date: 2018/3/13 16:01
 * @Description:
 */
public class MysqlDriver implements Driver {
    @Override
    public void connect() {
        System.out.println("connect mysql done!");
    }
}
