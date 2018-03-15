package design.patterns.facade;

/**
 * @author: andy
 * @Date: 2018/3/13 11:37
 * @Description:
 */
public class Cpu {
    public void startup() {
        System.out.println("cpu startup!");
    }

    public void shutdown() {
        System.out.println("cpu shutdown!");
    }
}
