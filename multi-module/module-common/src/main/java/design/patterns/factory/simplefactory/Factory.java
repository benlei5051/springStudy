package design.patterns.factory.simplefactory;

/**
 * @author: andy
 * @Date: 2018/3/12 14:33
 * @Description:
 */
public class Factory {
    public BMW createBMW(int type) {
        switch (type) {
            case 320:
                return new BMW320();
            case 523:
                return new BMW523();
            default:
                break;
        }
        return null;
    }
}
