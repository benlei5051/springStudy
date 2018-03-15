package design.patterns.facade;

/**
 * @author: andy
 * @Date: 2018/3/13 11:41
 * @Description:
 */
public class User {
    public static void main(String[] args){
        Computer computer = new Computer();
        computer.startup();
        computer.shutdown();
    }
}
