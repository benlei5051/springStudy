package design.patterns.proxy;

import design.patterns.adapter.classAdapter.Source;
import design.patterns.decorator.Sourceable;

/**
 * @author: andy
 * @Date: 2018/3/13 11:16
 * @Description:
 */
public class Proxy implements Sourceable{
    private Source source;

    public Proxy() {
        super();
        this.source =  new Source();
    }

    @Override
    public void method() {
        before();
        source.method1();
        after();

    }

    private void after() {
        System.out.println("after proxy!");
    }

    private void before() {
        System.out.println("before proxy!");
    }
}
