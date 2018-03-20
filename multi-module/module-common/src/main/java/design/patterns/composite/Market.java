package design.patterns.composite;

/**
 * @author: andy
 * @Date: 2018/3/19 15:25
 * @Description:
 */
public abstract class Market {
    protected String name;

    protected abstract void add(Market m);

    protected abstract void remove(Market m);

    protected abstract void payCard();

}
