package design.patterns.decorator;

/**
 * @author: andy
 * @Date: 2018/3/13 11:09
 * @Description:
 */
public class Decorator implements Sourceable{

    private Sourceable source;

    public Decorator(Sourceable source) {
        super();
        this.source = source;
    }

    @Override
    public void method() {
        System.out.println("before decorator!");
        source.method();
        System.out.println("after decorator!");

    }
}
