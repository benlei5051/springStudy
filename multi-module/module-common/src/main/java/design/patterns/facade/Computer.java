package design.patterns.facade;

/**
 * @author: andy
 * @Date: 2018/3/13 11:38
 * @Description:
 */
public class Computer {
    private Cpu cpu;
    private Disk disk;
    private Memory memory;

    public Computer() {
        this.cpu = new Cpu();
        this.disk = new Disk();
        this.memory = new Memory();
    }

    public void startup() {
        System.out.println("start the computer!");
        cpu.startup();
        memory.startup();
        disk.startup();
        System.out.println("start computer finished!");
    }

    public void shutdown() {
        System.out.println("begin to close the computer!");
        cpu.shutdown();
        memory.shutdown();
        disk.shutdown();
        System.out.println("computer closed!");
    }

}
