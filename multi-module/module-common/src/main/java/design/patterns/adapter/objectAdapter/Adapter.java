package design.patterns.adapter.objectAdapter;

import design.patterns.adapter.classAdapter.Source;
import design.patterns.adapter.classAdapter.Targetable;

/**
 * @author: andy
 * @Date: 2018/3/13 10:48
 * @Description: 适配器直接关联，而不是使用多继承或继承再实现
 */
public class Adapter implements Targetable{

    private Source source;

    public Adapter(Source source) {
        super();
        this.source = source;
    }

    @Override
    public void method2() {
        source.method1();
    }
}
