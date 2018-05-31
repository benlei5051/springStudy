package design.patterns.adapter.objectAdapter;

import design.patterns.adapter.classAdapter.Source;
import design.patterns.adapter.classAdapter.Targetable;

/**
 * @author: andy
 * @Date: 2018/3/13 10:53
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        Source source = new Source();
        Targetable target = new Adapter(source);
        target.method2();
    }
}
