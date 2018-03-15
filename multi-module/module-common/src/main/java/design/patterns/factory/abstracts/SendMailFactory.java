package design.patterns.factory.abstracts;

/**
 * @author: andy
 * @Date: 2018/3/12 15:02
 * @Description:
 */
public class SendMailFactory implements Provider{
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
