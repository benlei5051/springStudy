package design.patterns.factory.abstracts;

/**
 * @author: andy
 * @Date: 2018/3/12 15:03
 * @Description:
 */
public class SendSmsFactory implements Provider{
    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
