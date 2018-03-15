package design.patterns.factory.abstracts;

/**
 * @author: andy
 * @Date: 2018/3/12 14:59
 * @Description:
 */
public class MailSender implements Sender{
    @Override
    public void send() {
        System.out.println("this is mailsender!");
    }
}
