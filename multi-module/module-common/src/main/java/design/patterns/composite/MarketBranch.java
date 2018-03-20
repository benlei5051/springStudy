package design.patterns.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: andy
 * @Date: 2018/3/19 15:52
 * @Description:
 */
public class MarketBranch extends Market {

    private List<Market> list =new ArrayList<>();

    public MarketBranch(String name) {
        this.name = name;
    }

    @Override
    protected void add(Market company) {
        list.add(company);
    }

    @Override
    protected void remove(Market company) {
        list.remove(company);
    }

    @Override
    protected void payCard() {
        System.out.println(name + "消费,积分已累加入该会员卡");
        for (Market market : list) {
            market.payCard();
        }
    }

}
