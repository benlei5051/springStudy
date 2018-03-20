package design.patterns.Flyweight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @author: andy
 * @Date: 2018/3/19 16:51
 * @Description:
 */
public class ConnectPool {
    private Vector<Connection> pool;
    private String url = "jdbc:mysql://localhost:3306/test";
    private String username = "root";
    private String password = "root";
    private String driverClassName = "com.mysql.jdbc.Driver";
    private int poolsize = 100;
    private static ConnectPool instance = null;
    Connection conn = null;

    /* 构造方法，做一些初始化工作 */
    private ConnectPool() {
        pool = new Vector<Connection>(poolsize);

        for (int i = 0; i < poolsize; i++) {
            try {
                Class.forName(driverClassName);
                conn = DriverManager.getConnection(url, username, password);
                pool.add(conn);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /* 返回连接到连接池 */
    public synchronized void release() {
        pool.add(conn);
    }

    /* 返回连接池中的一个数据库连接 */
    public synchronized Connection getConnection() {
        if (pool.size() > 0) {
            Connection conn = pool.get(0);
            pool.remove(conn);
            return conn;
        } else {
            return null;
        }
    }

}
