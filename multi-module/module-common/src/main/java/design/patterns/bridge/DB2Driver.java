package design.patterns.bridge;

/**
 * @author: andy
 * @Date: 2018/3/13 16:02
 * @Description:
 */
public class DB2Driver implements Driver {
    @Override
    public void connect() {
        System.out.println("connect db2 done!");
    }
}
