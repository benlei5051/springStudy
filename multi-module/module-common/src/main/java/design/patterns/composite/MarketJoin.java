package design.patterns.composite;

/**
 * @author: andy
 * @Date: 2018/3/19 15:50
 * @Description:叶子节点
 */
public class MarketJoin extends Market {

    public MarketJoin(String name) {
        this.name = name;
    }

    @Override
    protected void add(Market m) {

    }

    @Override
    protected void remove(Market m) {

    }

    @Override
    protected void payCard() {
        System.out.println(name + "消费，积分已累加入该会员卡");
    }
}
