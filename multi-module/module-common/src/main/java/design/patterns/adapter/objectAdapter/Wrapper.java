package design.patterns.adapter.objectAdapter;

import design.patterns.adapter.classAdapter.Source;
import design.patterns.adapter.classAdapter.Targetable;

/**
 * @author: andy
 * @Date: 2018/3/13 10:48
 * @Description:
 */
public class Wrapper implements Targetable{

    private Source source;

    public Wrapper(Source source) {
        super();
        this.source = source;
    }

    @Override
    public void method1() {
        source.method1();
    }

    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }
}
